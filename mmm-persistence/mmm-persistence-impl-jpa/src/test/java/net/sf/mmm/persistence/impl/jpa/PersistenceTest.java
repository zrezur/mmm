/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.persistence.impl.jpa;

import java.util.concurrent.Callable;

import net.sf.mmm.persistence.api.PersistenceEntityManager;
import net.sf.mmm.persistence.api.PersistenceManager;
import net.sf.mmm.transaction.api.TransactionExecutor;
import net.sf.mmm.util.component.impl.SpringContainerPool;

import org.junit.Assert;
import org.junit.Test;

/**
 * This is the test-case for the persistence.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@SuppressWarnings("all")
public class PersistenceTest {

  protected PersistenceManager getPersistenceManager() {

    return SpringContainerPool.getInstance().getComponent(PersistenceManager.class);
  }

  @Test
  public void testPersistence() throws Exception {

    TransactionExecutor transactionExecutor = SpringContainerPool.getInstance().getComponent(
        TransactionExecutor.class);

    DummyFooEntity foo = transactionExecutor.doInTransaction(new Callable<DummyFooEntity>() {

      public DummyFooEntity call() throws Exception {

        return createAndSave();
      }
    });
    final Integer fooId = foo.getId();
    final Integer barId = foo.getBar().getId();
    transactionExecutor.doInTransaction(new Callable<Void>() {

      public Void call() throws Exception {

        readAndUpdate(fooId);
        return null;
      }
    });

    transactionExecutor.doInTransaction(new Callable<Void>() {

      public Void call() throws Exception {

        readAgainAndDelete(fooId, barId);
        return null;
      }
    });

  }

  protected void readAndUpdate(Integer fooId) {

    Assert.assertNotNull(fooId);
    PersistenceManager persistenceManager = getPersistenceManager();
    PersistenceEntityManager<DummyFooEntity> fooManager = persistenceManager
        .getManager(DummyFooEntity.class);
    DummyFooEntity foo = fooManager.load(fooId);
    Assert.assertEquals(42, foo.getNumber());
    DummyBarEntity bar = foo.getBar();
    Assert.assertNotNull(bar);
    Assert.assertEquals("value42", bar.getValue());
    foo.setNumber(24);
    DummyBarEntity bar2 = new DummyBarEntity();
    bar2.setValue("24value");
    PersistenceEntityManager<DummyBarEntity> barManager = persistenceManager
        .getManager(DummyBarEntity.class);
    barManager.save(bar2);
    foo.setBar(bar2);
  }

  protected void readAgainAndDelete(Integer fooId, Integer barId) {

    Assert.assertNotNull(fooId);
    PersistenceManager persistenceManager = getPersistenceManager();
    PersistenceEntityManager<DummyFooEntity> fooManager = persistenceManager
        .getManager(DummyFooEntity.class);
    DummyFooEntity foo = fooManager.load(fooId);
    Assert.assertEquals(24, foo.getNumber());
    DummyBarEntity bar2 = foo.getBar();
    Assert.assertNotNull(bar2);
    Assert.assertEquals("24value", bar2.getValue());
    PersistenceEntityManager<DummyBarEntity> barManager = persistenceManager
        .getManager(DummyBarEntity.class);
    Assert.assertNotNull(barId);
    DummyBarEntity bar = barManager.load(barId);
    Assert.assertEquals("value42", bar.getValue());
    barManager.delete(bar);
    fooManager.delete(foo);
  }

  protected DummyFooEntity createAndSave() {

    PersistenceManager persistenceManager = getPersistenceManager();
    PersistenceEntityManager<DummyFooEntity> fooManager = persistenceManager
        .getManager(DummyFooEntity.class);
    Assert.assertSame(DummyFooEntity.class, fooManager.getEntityClass());
    Assert.assertTrue(fooManager instanceof DummyFooEntityManager);
    PersistenceEntityManager<DummyBarEntity> barManager = persistenceManager
        .getManager(DummyBarEntity.class);
    Assert.assertSame(DummyBarEntity.class, barManager.getEntityClass());
    Assert.assertTrue(barManager instanceof DummyBarEntityManager);
    DummyBarEntity bar = new DummyBarEntity();
    bar.setValue("value42");
    barManager.save(bar);
    DummyFooEntity foo = new DummyFooEntity();
    foo.setNumber(42);
    foo.setBar(bar);
    fooManager.save(foo);
    return foo;
  }

}
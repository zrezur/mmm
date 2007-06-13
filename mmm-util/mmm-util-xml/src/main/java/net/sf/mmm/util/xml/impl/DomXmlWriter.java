/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.xml.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import net.sf.mmm.util.xml.NlsBundleXml;
import net.sf.mmm.util.xml.XmlException;
import net.sf.mmm.util.xml.XmlUtil;
import net.sf.mmm.util.xml.base.AbstractXmlWriter;

/**
 * This is an implementation of the
 * {@link net.sf.mmm.util.xml.api.XmlSerializer} interface that generates the
 * XML as {@link org.w3c.dom.Node DOM} tree. This can be done from scratch or
 * the XML can be appended to an element of an existing DOM tree.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public class DomXmlWriter extends AbstractXmlWriter {

  /** the XML DOM document */
  private Document document;

  /** the XML DOM element */
  private Element element;

  /** the current XML DOM element */
  public Element currentElement;

  /**
   * The constructor.
   * 
   * @param xmlDocument is the XML DOM document where to append the XML to
   *        serialize.
   */
  public DomXmlWriter(Document xmlDocument) {

    super();
    this.document = xmlDocument;
    this.element = null;
  }

  /**
   * The constructor.
   * 
   * @param xmlElement is the XML DOM element where to append the XML to
   *        serialize.
   */
  public DomXmlWriter(Element xmlElement) {

    super();
    this.element = xmlElement;
    this.currentElement = this.element;
    this.document = this.element.getOwnerDocument();
  }

  /**
   * {@inheritDoc}
   */
  public void writeStartElement(String localName, String namespacePrefix, String namespaceUri)
      throws XmlException {

    Element newElement;
    if ((namespacePrefix == null) && (namespaceUri == null)) {
      newElement = this.document.createElement(localName);
    } else {
      if (namespaceUri == null) {
        if (this.currentElement != null) {
          namespaceUri = this.currentElement.lookupNamespaceURI(namespacePrefix);
        }
        if (namespaceUri == null) {
          throw new XmlNamespacePrefixUndefinedException(namespacePrefix);
        }
      } else {
        if (namespacePrefix == null) {
          if (this.currentElement != null) {
            namespacePrefix = this.currentElement.lookupPrefix(namespaceUri);
          }
          if (namespacePrefix == null) {
            // Exception or build own prefix?
            throw new XmlException("No such Uri" + namespaceUri);
          }
        }
      }
      newElement = this.document.createElementNS(namespaceUri, createQualifiedName(namespacePrefix,
          localName));
    }
    if (this.currentElement == null) {
      if (this.document.getDocumentElement() != null) {
        throw new XmlException(NlsBundleXml.ERR_SECOND_ROOT);
      }
      this.document.appendChild(newElement);
    } else {
      this.currentElement.appendChild(newElement);
    }
    this.currentElement = newElement;
  }

  /**
   * {@inheritDoc}
   */
  public void writeAttribute(String name, String value, String namespacePrefix) throws XmlException {

    if (this.currentElement == null) {
      throw new XmlNoOpenElementException();
    }
    if (namespacePrefix == null) {
      this.currentElement.setAttribute(name, value);
    } else {
      String namespaceUri = this.currentElement.lookupNamespaceURI(namespacePrefix);
      if (namespaceUri == null) {
        throw new XmlNamespacePrefixUndefinedException(namespacePrefix);
      }
      this.currentElement.setAttributeNS(namespaceUri, createQualifiedName(namespacePrefix, name),
          value);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void writeCharacters(String text) throws XmlException {

    if (this.currentElement == null) {
      throw new XmlNoOpenElementException();
    }
    this.currentElement.appendChild(this.document.createTextNode(text));
  }

  /**
   * {@inheritDoc}
   */
  public void writeCData(String text) throws XmlException {

    if (this.currentElement == null) {
      throw new XmlNoOpenElementException();
    }
    this.currentElement.appendChild(this.document.createCDATASection(text));
  }

  /**
   * {@inheritDoc}
   */
  public void writeEndElement(String localName, String namespacePrefix) throws XmlException {

    if (this.currentElement == null) {
      throw new XmlNoOpenElementException();
    }
    String openTagName = this.currentElement.getTagName();
    String qualifiedName = createQualifiedName(namespacePrefix, localName);
    if (!qualifiedName.equals(openTagName)) {
      throw new XmlCloseElementException(openTagName, qualifiedName);
    }
    Node parentNode = this.currentElement.getParentNode();
    if (parentNode == this.element) {
      throw new XmlException(NlsBundleXml.ERR_CLOSE_TOPLEVEL);
    }
    if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
      this.currentElement = (Element) parentNode;
    } else if (parentNode.getNodeType() == Node.DOCUMENT_NODE) {
      this.currentElement = null;
    } else {
      throw new XmlException(NlsBundleXml.ERR_INVALID_XML);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void writeComment(String text) throws XmlException {

    if (this.currentElement == null) {
      throw new XmlNoOpenElementException();
    }
    this.currentElement.appendChild(this.document.createComment(text));
  }

  /**
   * {@inheritDoc}
   */
  public void writeNamespaceDeclaration(String namespaceKey, String namespaceUri)
      throws XmlException {

    this.currentElement.setAttributeNS(XmlUtil.NAMESPACE_URI_XMLNS, createQualifiedName(
        XmlUtil.NAMESPACE_PREFIX_XMLNS, namespaceKey), namespaceUri);
  }
}

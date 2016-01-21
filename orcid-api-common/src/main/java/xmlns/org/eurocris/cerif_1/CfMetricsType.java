//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.26 at 11:29:18 AM GMT 
//


package xmlns.org.eurocris.cerif_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for cfMetrics__Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cfMetrics__Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cfMetricsId" type="{urn:xmlns:org:eurocris:cerif-1.6-2}cfId__Type"/>
 *         &lt;element name="cfURI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="cfName" type="{urn:xmlns:org:eurocris:cerif-1.6-2}cfMLangString__Type"/>
 *           &lt;element name="cfDescr" type="{urn:xmlns:org:eurocris:cerif-1.6-2}cfMLangString__Type"/>
 *           &lt;element name="cfKeyw" type="{urn:xmlns:org:eurocris:cerif-1.6-2}cfMLangString__Type"/>
 *           &lt;element name="cfMetrics_Class" type="{urn:xmlns:org:eurocris:cerif-1.6-2}cfCoreClassWithFraction__Type"/>
 *           &lt;element name="cfResPubl_Metrics">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="cfResPublId" type="{urn:xmlns:org:eurocris:cerif-1.6-2}cfId__Type"/>
 *                     &lt;group ref="{urn:xmlns:org:eurocris:cerif-1.6-2}cfCoreClassWithFraction__Group"/>
 *                     &lt;element name="cfYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                     &lt;element name="cfCount" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="cfFedId" type="{urn:xmlns:org:eurocris:cerif-1.6-2}cfFedId__EmbType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cfMetrics__Type", propOrder = {
    "cfMetricsId",
    "cfURI",
    "cfNameOrCfDescrOrCfKeyw"
})
public class CfMetricsType {

    @XmlElement(required = true)
    protected String cfMetricsId;
    protected String cfURI;
    @XmlElementRefs({
        @XmlElementRef(name = "cfMetrics_Class", namespace = "urn:xmlns:org:eurocris:cerif-1.6-2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cfResPubl_Metrics", namespace = "urn:xmlns:org:eurocris:cerif-1.6-2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cfFedId", namespace = "urn:xmlns:org:eurocris:cerif-1.6-2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cfKeyw", namespace = "urn:xmlns:org:eurocris:cerif-1.6-2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cfDescr", namespace = "urn:xmlns:org:eurocris:cerif-1.6-2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cfName", namespace = "urn:xmlns:org:eurocris:cerif-1.6-2", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> cfNameOrCfDescrOrCfKeyw;

    /**
     * Gets the value of the cfMetricsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfMetricsId() {
        return cfMetricsId;
    }

    /**
     * Sets the value of the cfMetricsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfMetricsId(String value) {
        this.cfMetricsId = value;
    }

    /**
     * Gets the value of the cfURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfURI() {
        return cfURI;
    }

    /**
     * Sets the value of the cfURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfURI(String value) {
        this.cfURI = value;
    }

    /**
     * Gets the value of the cfNameOrCfDescrOrCfKeyw property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cfNameOrCfDescrOrCfKeyw property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCfNameOrCfDescrOrCfKeyw().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link CfMLangStringType }{@code >}
     * {@link JAXBElement }{@code <}{@link CfCoreClassWithFractionType }{@code >}
     * {@link JAXBElement }{@code <}{@link CfMLangStringType }{@code >}
     * {@link JAXBElement }{@code <}{@link CfMLangStringType }{@code >}
     * {@link JAXBElement }{@code <}{@link CfMetricsType.CfResPublMetrics }{@code >}
     * {@link JAXBElement }{@code <}{@link CfFedIdEmbType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getCfNameOrCfDescrOrCfKeyw() {
        if (cfNameOrCfDescrOrCfKeyw == null) {
            cfNameOrCfDescrOrCfKeyw = new ArrayList<JAXBElement<?>>();
        }
        return this.cfNameOrCfDescrOrCfKeyw;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="cfResPublId" type="{urn:xmlns:org:eurocris:cerif-1.6-2}cfId__Type"/>
     *         &lt;group ref="{urn:xmlns:org:eurocris:cerif-1.6-2}cfCoreClassWithFraction__Group"/>
     *         &lt;element name="cfYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="cfCount" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "cfResPublId",
        "cfClassId",
        "cfClassSchemeId",
        "cfStartDate",
        "cfEndDate",
        "cfFraction",
        "cfYear",
        "cfCount"
    })
    public static class CfResPublMetrics {

        @XmlElement(required = true)
        protected String cfResPublId;
        @XmlElement(required = true)
        protected String cfClassId;
        @XmlElement(required = true)
        protected String cfClassSchemeId;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar cfStartDate;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar cfEndDate;
        protected Float cfFraction;
        protected Integer cfYear;
        protected Float cfCount;

        /**
         * Gets the value of the cfResPublId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCfResPublId() {
            return cfResPublId;
        }

        /**
         * Sets the value of the cfResPublId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCfResPublId(String value) {
            this.cfResPublId = value;
        }

        /**
         * Gets the value of the cfClassId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCfClassId() {
            return cfClassId;
        }

        /**
         * Sets the value of the cfClassId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCfClassId(String value) {
            this.cfClassId = value;
        }

        /**
         * Gets the value of the cfClassSchemeId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCfClassSchemeId() {
            return cfClassSchemeId;
        }

        /**
         * Sets the value of the cfClassSchemeId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCfClassSchemeId(String value) {
            this.cfClassSchemeId = value;
        }

        /**
         * Gets the value of the cfStartDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getCfStartDate() {
            return cfStartDate;
        }

        /**
         * Sets the value of the cfStartDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setCfStartDate(XMLGregorianCalendar value) {
            this.cfStartDate = value;
        }

        /**
         * Gets the value of the cfEndDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getCfEndDate() {
            return cfEndDate;
        }

        /**
         * Sets the value of the cfEndDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setCfEndDate(XMLGregorianCalendar value) {
            this.cfEndDate = value;
        }

        /**
         * Gets the value of the cfFraction property.
         * 
         * @return
         *     possible object is
         *     {@link Float }
         *     
         */
        public Float getCfFraction() {
            return cfFraction;
        }

        /**
         * Sets the value of the cfFraction property.
         * 
         * @param value
         *     allowed object is
         *     {@link Float }
         *     
         */
        public void setCfFraction(Float value) {
            this.cfFraction = value;
        }

        /**
         * Gets the value of the cfYear property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getCfYear() {
            return cfYear;
        }

        /**
         * Sets the value of the cfYear property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setCfYear(Integer value) {
            this.cfYear = value;
        }

        /**
         * Gets the value of the cfCount property.
         * 
         * @return
         *     possible object is
         *     {@link Float }
         *     
         */
        public Float getCfCount() {
            return cfCount;
        }

        /**
         * Sets the value of the cfCount property.
         * 
         * @param value
         *     allowed object is
         *     {@link Float }
         *     
         */
        public void setCfCount(Float value) {
            this.cfCount = value;
        }

    }

}
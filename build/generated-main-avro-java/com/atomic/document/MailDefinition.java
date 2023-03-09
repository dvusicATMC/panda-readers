/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.atomic.document;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class MailDefinition extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8583739157854672556L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MailDefinition\",\"namespace\":\"com.atomic.document\",\"fields\":[{\"name\":\"mailTo\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]},{\"name\":\"mailFrom\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"mailCC\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]},{\"name\":\"mailBCC\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]},{\"name\":\"replyTo\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address\"},{\"name\":\"sentDate\",\"type\":[\"null\",{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}]},{\"name\":\"receivedDate\",\"type\":[\"null\",{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
  }

  private static final BinaryMessageEncoder<MailDefinition> ENCODER =
      new BinaryMessageEncoder<MailDefinition>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<MailDefinition> DECODER =
      new BinaryMessageDecoder<MailDefinition>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<MailDefinition> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<MailDefinition> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<MailDefinition> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<MailDefinition>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this MailDefinition to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a MailDefinition from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a MailDefinition instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static MailDefinition fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.util.List<java.lang.String> mailTo;
  private java.lang.String mailFrom;
  private java.util.List<java.lang.String> mailCC;
  private java.util.List<java.lang.String> mailBCC;
  /** Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address */
  private java.lang.String replyTo;
  private java.time.Instant sentDate;
  private java.time.Instant receivedDate;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public MailDefinition() {}

  /**
   * All-args constructor.
   * @param mailTo The new value for mailTo
   * @param mailFrom The new value for mailFrom
   * @param mailCC The new value for mailCC
   * @param mailBCC The new value for mailBCC
   * @param replyTo Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address
   * @param sentDate The new value for sentDate
   * @param receivedDate The new value for receivedDate
   */
  public MailDefinition(java.util.List<java.lang.String> mailTo, java.lang.String mailFrom, java.util.List<java.lang.String> mailCC, java.util.List<java.lang.String> mailBCC, java.lang.String replyTo, java.time.Instant sentDate, java.time.Instant receivedDate) {
    this.mailTo = mailTo;
    this.mailFrom = mailFrom;
    this.mailCC = mailCC;
    this.mailBCC = mailBCC;
    this.replyTo = replyTo;
    this.sentDate = sentDate;
    this.receivedDate = receivedDate;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return mailTo;
    case 1: return mailFrom;
    case 2: return mailCC;
    case 3: return mailBCC;
    case 4: return replyTo;
    case 5: return sentDate;
    case 6: return receivedDate;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: mailTo = (java.util.List<java.lang.String>)value$; break;
    case 1: mailFrom = value$ != null ? value$.toString() : null; break;
    case 2: mailCC = (java.util.List<java.lang.String>)value$; break;
    case 3: mailBCC = (java.util.List<java.lang.String>)value$; break;
    case 4: replyTo = value$ != null ? value$.toString() : null; break;
    case 5: sentDate = (java.time.Instant)value$; break;
    case 6: receivedDate = (java.time.Instant)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'mailTo' field.
   * @return The value of the 'mailTo' field.
   */
  public java.util.List<java.lang.String> getMailTo() {
    return mailTo;
  }


  /**
   * Sets the value of the 'mailTo' field.
   * @param value the value to set.
   */
  public void setMailTo(java.util.List<java.lang.String> value) {
    this.mailTo = value;
  }

  /**
   * Gets the value of the 'mailFrom' field.
   * @return The value of the 'mailFrom' field.
   */
  public java.lang.String getMailFrom() {
    return mailFrom;
  }


  /**
   * Sets the value of the 'mailFrom' field.
   * @param value the value to set.
   */
  public void setMailFrom(java.lang.String value) {
    this.mailFrom = value;
  }

  /**
   * Gets the value of the 'mailCC' field.
   * @return The value of the 'mailCC' field.
   */
  public java.util.List<java.lang.String> getMailCC() {
    return mailCC;
  }


  /**
   * Sets the value of the 'mailCC' field.
   * @param value the value to set.
   */
  public void setMailCC(java.util.List<java.lang.String> value) {
    this.mailCC = value;
  }

  /**
   * Gets the value of the 'mailBCC' field.
   * @return The value of the 'mailBCC' field.
   */
  public java.util.List<java.lang.String> getMailBCC() {
    return mailBCC;
  }


  /**
   * Sets the value of the 'mailBCC' field.
   * @param value the value to set.
   */
  public void setMailBCC(java.util.List<java.lang.String> value) {
    this.mailBCC = value;
  }

  /**
   * Gets the value of the 'replyTo' field.
   * @return Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address
   */
  public java.lang.String getReplyTo() {
    return replyTo;
  }


  /**
   * Sets the value of the 'replyTo' field.
   * Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address
   * @param value the value to set.
   */
  public void setReplyTo(java.lang.String value) {
    this.replyTo = value;
  }

  /**
   * Gets the value of the 'sentDate' field.
   * @return The value of the 'sentDate' field.
   */
  public java.time.Instant getSentDate() {
    return sentDate;
  }


  /**
   * Sets the value of the 'sentDate' field.
   * @param value the value to set.
   */
  public void setSentDate(java.time.Instant value) {
    this.sentDate = value;
  }

  /**
   * Gets the value of the 'receivedDate' field.
   * @return The value of the 'receivedDate' field.
   */
  public java.time.Instant getReceivedDate() {
    return receivedDate;
  }


  /**
   * Sets the value of the 'receivedDate' field.
   * @param value the value to set.
   */
  public void setReceivedDate(java.time.Instant value) {
    this.receivedDate = value;
  }

  /**
   * Creates a new MailDefinition RecordBuilder.
   * @return A new MailDefinition RecordBuilder
   */
  public static com.atomic.document.MailDefinition.Builder newBuilder() {
    return new com.atomic.document.MailDefinition.Builder();
  }

  /**
   * Creates a new MailDefinition RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new MailDefinition RecordBuilder
   */
  public static com.atomic.document.MailDefinition.Builder newBuilder(com.atomic.document.MailDefinition.Builder other) {
    if (other == null) {
      return new com.atomic.document.MailDefinition.Builder();
    } else {
      return new com.atomic.document.MailDefinition.Builder(other);
    }
  }

  /**
   * Creates a new MailDefinition RecordBuilder by copying an existing MailDefinition instance.
   * @param other The existing instance to copy.
   * @return A new MailDefinition RecordBuilder
   */
  public static com.atomic.document.MailDefinition.Builder newBuilder(com.atomic.document.MailDefinition other) {
    if (other == null) {
      return new com.atomic.document.MailDefinition.Builder();
    } else {
      return new com.atomic.document.MailDefinition.Builder(other);
    }
  }

  /**
   * RecordBuilder for MailDefinition instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MailDefinition>
    implements org.apache.avro.data.RecordBuilder<MailDefinition> {

    private java.util.List<java.lang.String> mailTo;
    private java.lang.String mailFrom;
    private java.util.List<java.lang.String> mailCC;
    private java.util.List<java.lang.String> mailBCC;
    /** Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address */
    private java.lang.String replyTo;
    private java.time.Instant sentDate;
    private java.time.Instant receivedDate;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.atomic.document.MailDefinition.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.mailTo)) {
        this.mailTo = data().deepCopy(fields()[0].schema(), other.mailTo);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.mailFrom)) {
        this.mailFrom = data().deepCopy(fields()[1].schema(), other.mailFrom);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.mailCC)) {
        this.mailCC = data().deepCopy(fields()[2].schema(), other.mailCC);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.mailBCC)) {
        this.mailBCC = data().deepCopy(fields()[3].schema(), other.mailBCC);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.replyTo)) {
        this.replyTo = data().deepCopy(fields()[4].schema(), other.replyTo);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.sentDate)) {
        this.sentDate = data().deepCopy(fields()[5].schema(), other.sentDate);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.receivedDate)) {
        this.receivedDate = data().deepCopy(fields()[6].schema(), other.receivedDate);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
    }

    /**
     * Creates a Builder by copying an existing MailDefinition instance
     * @param other The existing instance to copy.
     */
    private Builder(com.atomic.document.MailDefinition other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.mailTo)) {
        this.mailTo = data().deepCopy(fields()[0].schema(), other.mailTo);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.mailFrom)) {
        this.mailFrom = data().deepCopy(fields()[1].schema(), other.mailFrom);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.mailCC)) {
        this.mailCC = data().deepCopy(fields()[2].schema(), other.mailCC);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.mailBCC)) {
        this.mailBCC = data().deepCopy(fields()[3].schema(), other.mailBCC);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.replyTo)) {
        this.replyTo = data().deepCopy(fields()[4].schema(), other.replyTo);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.sentDate)) {
        this.sentDate = data().deepCopy(fields()[5].schema(), other.sentDate);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.receivedDate)) {
        this.receivedDate = data().deepCopy(fields()[6].schema(), other.receivedDate);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'mailTo' field.
      * @return The value.
      */
    public java.util.List<java.lang.String> getMailTo() {
      return mailTo;
    }


    /**
      * Sets the value of the 'mailTo' field.
      * @param value The value of 'mailTo'.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder setMailTo(java.util.List<java.lang.String> value) {
      validate(fields()[0], value);
      this.mailTo = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'mailTo' field has been set.
      * @return True if the 'mailTo' field has been set, false otherwise.
      */
    public boolean hasMailTo() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'mailTo' field.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder clearMailTo() {
      mailTo = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'mailFrom' field.
      * @return The value.
      */
    public java.lang.String getMailFrom() {
      return mailFrom;
    }


    /**
      * Sets the value of the 'mailFrom' field.
      * @param value The value of 'mailFrom'.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder setMailFrom(java.lang.String value) {
      validate(fields()[1], value);
      this.mailFrom = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'mailFrom' field has been set.
      * @return True if the 'mailFrom' field has been set, false otherwise.
      */
    public boolean hasMailFrom() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'mailFrom' field.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder clearMailFrom() {
      mailFrom = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'mailCC' field.
      * @return The value.
      */
    public java.util.List<java.lang.String> getMailCC() {
      return mailCC;
    }


    /**
      * Sets the value of the 'mailCC' field.
      * @param value The value of 'mailCC'.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder setMailCC(java.util.List<java.lang.String> value) {
      validate(fields()[2], value);
      this.mailCC = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'mailCC' field has been set.
      * @return True if the 'mailCC' field has been set, false otherwise.
      */
    public boolean hasMailCC() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'mailCC' field.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder clearMailCC() {
      mailCC = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'mailBCC' field.
      * @return The value.
      */
    public java.util.List<java.lang.String> getMailBCC() {
      return mailBCC;
    }


    /**
      * Sets the value of the 'mailBCC' field.
      * @param value The value of 'mailBCC'.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder setMailBCC(java.util.List<java.lang.String> value) {
      validate(fields()[3], value);
      this.mailBCC = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'mailBCC' field has been set.
      * @return True if the 'mailBCC' field has been set, false otherwise.
      */
    public boolean hasMailBCC() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'mailBCC' field.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder clearMailBCC() {
      mailBCC = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'replyTo' field.
      * Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address
      * @return The value.
      */
    public java.lang.String getReplyTo() {
      return replyTo;
    }


    /**
      * Sets the value of the 'replyTo' field.
      * Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address
      * @param value The value of 'replyTo'.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder setReplyTo(java.lang.String value) {
      validate(fields()[4], value);
      this.replyTo = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'replyTo' field has been set.
      * Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address
      * @return True if the 'replyTo' field has been set, false otherwise.
      */
    public boolean hasReplyTo() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'replyTo' field.
      * Addresses to which replies should be directed. This will usually be the sender of the message, but some messages may direct replies to a different address
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder clearReplyTo() {
      replyTo = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'sentDate' field.
      * @return The value.
      */
    public java.time.Instant getSentDate() {
      return sentDate;
    }


    /**
      * Sets the value of the 'sentDate' field.
      * @param value The value of 'sentDate'.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder setSentDate(java.time.Instant value) {
      validate(fields()[5], value);
      this.sentDate = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'sentDate' field has been set.
      * @return True if the 'sentDate' field has been set, false otherwise.
      */
    public boolean hasSentDate() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'sentDate' field.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder clearSentDate() {
      sentDate = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'receivedDate' field.
      * @return The value.
      */
    public java.time.Instant getReceivedDate() {
      return receivedDate;
    }


    /**
      * Sets the value of the 'receivedDate' field.
      * @param value The value of 'receivedDate'.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder setReceivedDate(java.time.Instant value) {
      validate(fields()[6], value);
      this.receivedDate = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'receivedDate' field has been set.
      * @return True if the 'receivedDate' field has been set, false otherwise.
      */
    public boolean hasReceivedDate() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'receivedDate' field.
      * @return This builder.
      */
    public com.atomic.document.MailDefinition.Builder clearReceivedDate() {
      receivedDate = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MailDefinition build() {
      try {
        MailDefinition record = new MailDefinition();
        record.mailTo = fieldSetFlags()[0] ? this.mailTo : (java.util.List<java.lang.String>) defaultValue(fields()[0]);
        record.mailFrom = fieldSetFlags()[1] ? this.mailFrom : (java.lang.String) defaultValue(fields()[1]);
        record.mailCC = fieldSetFlags()[2] ? this.mailCC : (java.util.List<java.lang.String>) defaultValue(fields()[2]);
        record.mailBCC = fieldSetFlags()[3] ? this.mailBCC : (java.util.List<java.lang.String>) defaultValue(fields()[3]);
        record.replyTo = fieldSetFlags()[4] ? this.replyTo : (java.lang.String) defaultValue(fields()[4]);
        record.sentDate = fieldSetFlags()[5] ? this.sentDate : (java.time.Instant) defaultValue(fields()[5]);
        record.receivedDate = fieldSetFlags()[6] ? this.receivedDate : (java.time.Instant) defaultValue(fields()[6]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<MailDefinition>
    WRITER$ = (org.apache.avro.io.DatumWriter<MailDefinition>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<MailDefinition>
    READER$ = (org.apache.avro.io.DatumReader<MailDefinition>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










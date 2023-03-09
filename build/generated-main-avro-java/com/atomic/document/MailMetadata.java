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
public class MailMetadata extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8136951836891994291L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MailMetadata\",\"namespace\":\"com.atomic.document\",\"fields\":[{\"name\":\"folderName\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"isAnswered\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"isDeleted\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"isDraft\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"isRecent\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"isSeen\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"isUser\",\"type\":[\"null\",\"boolean\"],\"doc\":\"A special flag that indicates that this folder supports user defined flags.\"},{\"name\":\"isExpunged\",\"type\":[\"null\",\"boolean\"],\"doc\":\"Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked \\\"expunged\\\" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder.\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<MailMetadata> ENCODER =
      new BinaryMessageEncoder<MailMetadata>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<MailMetadata> DECODER =
      new BinaryMessageDecoder<MailMetadata>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<MailMetadata> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<MailMetadata> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<MailMetadata> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<MailMetadata>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this MailMetadata to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a MailMetadata from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a MailMetadata instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static MailMetadata fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String folderName;
  private java.lang.Boolean isAnswered;
  private java.lang.Boolean isDeleted;
  private java.lang.Boolean isDraft;
  private java.lang.Boolean isRecent;
  private java.lang.Boolean isSeen;
  /** A special flag that indicates that this folder supports user defined flags. */
  private java.lang.Boolean isUser;
  /** Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked "expunged" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder. */
  private java.lang.Boolean isExpunged;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public MailMetadata() {}

  /**
   * All-args constructor.
   * @param folderName The new value for folderName
   * @param isAnswered The new value for isAnswered
   * @param isDeleted The new value for isDeleted
   * @param isDraft The new value for isDraft
   * @param isRecent The new value for isRecent
   * @param isSeen The new value for isSeen
   * @param isUser A special flag that indicates that this folder supports user defined flags.
   * @param isExpunged Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked "expunged" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder.
   */
  public MailMetadata(java.lang.String folderName, java.lang.Boolean isAnswered, java.lang.Boolean isDeleted, java.lang.Boolean isDraft, java.lang.Boolean isRecent, java.lang.Boolean isSeen, java.lang.Boolean isUser, java.lang.Boolean isExpunged) {
    this.folderName = folderName;
    this.isAnswered = isAnswered;
    this.isDeleted = isDeleted;
    this.isDraft = isDraft;
    this.isRecent = isRecent;
    this.isSeen = isSeen;
    this.isUser = isUser;
    this.isExpunged = isExpunged;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return folderName;
    case 1: return isAnswered;
    case 2: return isDeleted;
    case 3: return isDraft;
    case 4: return isRecent;
    case 5: return isSeen;
    case 6: return isUser;
    case 7: return isExpunged;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: folderName = value$ != null ? value$.toString() : null; break;
    case 1: isAnswered = (java.lang.Boolean)value$; break;
    case 2: isDeleted = (java.lang.Boolean)value$; break;
    case 3: isDraft = (java.lang.Boolean)value$; break;
    case 4: isRecent = (java.lang.Boolean)value$; break;
    case 5: isSeen = (java.lang.Boolean)value$; break;
    case 6: isUser = (java.lang.Boolean)value$; break;
    case 7: isExpunged = (java.lang.Boolean)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'folderName' field.
   * @return The value of the 'folderName' field.
   */
  public java.lang.String getFolderName() {
    return folderName;
  }


  /**
   * Sets the value of the 'folderName' field.
   * @param value the value to set.
   */
  public void setFolderName(java.lang.String value) {
    this.folderName = value;
  }

  /**
   * Gets the value of the 'isAnswered' field.
   * @return The value of the 'isAnswered' field.
   */
  public java.lang.Boolean getIsAnswered() {
    return isAnswered;
  }


  /**
   * Sets the value of the 'isAnswered' field.
   * @param value the value to set.
   */
  public void setIsAnswered(java.lang.Boolean value) {
    this.isAnswered = value;
  }

  /**
   * Gets the value of the 'isDeleted' field.
   * @return The value of the 'isDeleted' field.
   */
  public java.lang.Boolean getIsDeleted() {
    return isDeleted;
  }


  /**
   * Sets the value of the 'isDeleted' field.
   * @param value the value to set.
   */
  public void setIsDeleted(java.lang.Boolean value) {
    this.isDeleted = value;
  }

  /**
   * Gets the value of the 'isDraft' field.
   * @return The value of the 'isDraft' field.
   */
  public java.lang.Boolean getIsDraft() {
    return isDraft;
  }


  /**
   * Sets the value of the 'isDraft' field.
   * @param value the value to set.
   */
  public void setIsDraft(java.lang.Boolean value) {
    this.isDraft = value;
  }

  /**
   * Gets the value of the 'isRecent' field.
   * @return The value of the 'isRecent' field.
   */
  public java.lang.Boolean getIsRecent() {
    return isRecent;
  }


  /**
   * Sets the value of the 'isRecent' field.
   * @param value the value to set.
   */
  public void setIsRecent(java.lang.Boolean value) {
    this.isRecent = value;
  }

  /**
   * Gets the value of the 'isSeen' field.
   * @return The value of the 'isSeen' field.
   */
  public java.lang.Boolean getIsSeen() {
    return isSeen;
  }


  /**
   * Sets the value of the 'isSeen' field.
   * @param value the value to set.
   */
  public void setIsSeen(java.lang.Boolean value) {
    this.isSeen = value;
  }

  /**
   * Gets the value of the 'isUser' field.
   * @return A special flag that indicates that this folder supports user defined flags.
   */
  public java.lang.Boolean getIsUser() {
    return isUser;
  }


  /**
   * Sets the value of the 'isUser' field.
   * A special flag that indicates that this folder supports user defined flags.
   * @param value the value to set.
   */
  public void setIsUser(java.lang.Boolean value) {
    this.isUser = value;
  }

  /**
   * Gets the value of the 'isExpunged' field.
   * @return Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked "expunged" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder.
   */
  public java.lang.Boolean getIsExpunged() {
    return isExpunged;
  }


  /**
   * Sets the value of the 'isExpunged' field.
   * Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked "expunged" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder.
   * @param value the value to set.
   */
  public void setIsExpunged(java.lang.Boolean value) {
    this.isExpunged = value;
  }

  /**
   * Creates a new MailMetadata RecordBuilder.
   * @return A new MailMetadata RecordBuilder
   */
  public static com.atomic.document.MailMetadata.Builder newBuilder() {
    return new com.atomic.document.MailMetadata.Builder();
  }

  /**
   * Creates a new MailMetadata RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new MailMetadata RecordBuilder
   */
  public static com.atomic.document.MailMetadata.Builder newBuilder(com.atomic.document.MailMetadata.Builder other) {
    if (other == null) {
      return new com.atomic.document.MailMetadata.Builder();
    } else {
      return new com.atomic.document.MailMetadata.Builder(other);
    }
  }

  /**
   * Creates a new MailMetadata RecordBuilder by copying an existing MailMetadata instance.
   * @param other The existing instance to copy.
   * @return A new MailMetadata RecordBuilder
   */
  public static com.atomic.document.MailMetadata.Builder newBuilder(com.atomic.document.MailMetadata other) {
    if (other == null) {
      return new com.atomic.document.MailMetadata.Builder();
    } else {
      return new com.atomic.document.MailMetadata.Builder(other);
    }
  }

  /**
   * RecordBuilder for MailMetadata instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MailMetadata>
    implements org.apache.avro.data.RecordBuilder<MailMetadata> {

    private java.lang.String folderName;
    private java.lang.Boolean isAnswered;
    private java.lang.Boolean isDeleted;
    private java.lang.Boolean isDraft;
    private java.lang.Boolean isRecent;
    private java.lang.Boolean isSeen;
    /** A special flag that indicates that this folder supports user defined flags. */
    private java.lang.Boolean isUser;
    /** Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked "expunged" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder. */
    private java.lang.Boolean isExpunged;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.atomic.document.MailMetadata.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.folderName)) {
        this.folderName = data().deepCopy(fields()[0].schema(), other.folderName);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.isAnswered)) {
        this.isAnswered = data().deepCopy(fields()[1].schema(), other.isAnswered);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.isDeleted)) {
        this.isDeleted = data().deepCopy(fields()[2].schema(), other.isDeleted);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.isDraft)) {
        this.isDraft = data().deepCopy(fields()[3].schema(), other.isDraft);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.isRecent)) {
        this.isRecent = data().deepCopy(fields()[4].schema(), other.isRecent);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.isSeen)) {
        this.isSeen = data().deepCopy(fields()[5].schema(), other.isSeen);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.isUser)) {
        this.isUser = data().deepCopy(fields()[6].schema(), other.isUser);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.isExpunged)) {
        this.isExpunged = data().deepCopy(fields()[7].schema(), other.isExpunged);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing MailMetadata instance
     * @param other The existing instance to copy.
     */
    private Builder(com.atomic.document.MailMetadata other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.folderName)) {
        this.folderName = data().deepCopy(fields()[0].schema(), other.folderName);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.isAnswered)) {
        this.isAnswered = data().deepCopy(fields()[1].schema(), other.isAnswered);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.isDeleted)) {
        this.isDeleted = data().deepCopy(fields()[2].schema(), other.isDeleted);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.isDraft)) {
        this.isDraft = data().deepCopy(fields()[3].schema(), other.isDraft);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.isRecent)) {
        this.isRecent = data().deepCopy(fields()[4].schema(), other.isRecent);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.isSeen)) {
        this.isSeen = data().deepCopy(fields()[5].schema(), other.isSeen);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.isUser)) {
        this.isUser = data().deepCopy(fields()[6].schema(), other.isUser);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.isExpunged)) {
        this.isExpunged = data().deepCopy(fields()[7].schema(), other.isExpunged);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'folderName' field.
      * @return The value.
      */
    public java.lang.String getFolderName() {
      return folderName;
    }


    /**
      * Sets the value of the 'folderName' field.
      * @param value The value of 'folderName'.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder setFolderName(java.lang.String value) {
      validate(fields()[0], value);
      this.folderName = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'folderName' field has been set.
      * @return True if the 'folderName' field has been set, false otherwise.
      */
    public boolean hasFolderName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'folderName' field.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder clearFolderName() {
      folderName = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'isAnswered' field.
      * @return The value.
      */
    public java.lang.Boolean getIsAnswered() {
      return isAnswered;
    }


    /**
      * Sets the value of the 'isAnswered' field.
      * @param value The value of 'isAnswered'.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder setIsAnswered(java.lang.Boolean value) {
      validate(fields()[1], value);
      this.isAnswered = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'isAnswered' field has been set.
      * @return True if the 'isAnswered' field has been set, false otherwise.
      */
    public boolean hasIsAnswered() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'isAnswered' field.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder clearIsAnswered() {
      isAnswered = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'isDeleted' field.
      * @return The value.
      */
    public java.lang.Boolean getIsDeleted() {
      return isDeleted;
    }


    /**
      * Sets the value of the 'isDeleted' field.
      * @param value The value of 'isDeleted'.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder setIsDeleted(java.lang.Boolean value) {
      validate(fields()[2], value);
      this.isDeleted = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'isDeleted' field has been set.
      * @return True if the 'isDeleted' field has been set, false otherwise.
      */
    public boolean hasIsDeleted() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'isDeleted' field.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder clearIsDeleted() {
      isDeleted = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'isDraft' field.
      * @return The value.
      */
    public java.lang.Boolean getIsDraft() {
      return isDraft;
    }


    /**
      * Sets the value of the 'isDraft' field.
      * @param value The value of 'isDraft'.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder setIsDraft(java.lang.Boolean value) {
      validate(fields()[3], value);
      this.isDraft = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'isDraft' field has been set.
      * @return True if the 'isDraft' field has been set, false otherwise.
      */
    public boolean hasIsDraft() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'isDraft' field.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder clearIsDraft() {
      isDraft = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'isRecent' field.
      * @return The value.
      */
    public java.lang.Boolean getIsRecent() {
      return isRecent;
    }


    /**
      * Sets the value of the 'isRecent' field.
      * @param value The value of 'isRecent'.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder setIsRecent(java.lang.Boolean value) {
      validate(fields()[4], value);
      this.isRecent = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'isRecent' field has been set.
      * @return True if the 'isRecent' field has been set, false otherwise.
      */
    public boolean hasIsRecent() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'isRecent' field.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder clearIsRecent() {
      isRecent = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'isSeen' field.
      * @return The value.
      */
    public java.lang.Boolean getIsSeen() {
      return isSeen;
    }


    /**
      * Sets the value of the 'isSeen' field.
      * @param value The value of 'isSeen'.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder setIsSeen(java.lang.Boolean value) {
      validate(fields()[5], value);
      this.isSeen = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'isSeen' field has been set.
      * @return True if the 'isSeen' field has been set, false otherwise.
      */
    public boolean hasIsSeen() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'isSeen' field.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder clearIsSeen() {
      isSeen = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'isUser' field.
      * A special flag that indicates that this folder supports user defined flags.
      * @return The value.
      */
    public java.lang.Boolean getIsUser() {
      return isUser;
    }


    /**
      * Sets the value of the 'isUser' field.
      * A special flag that indicates that this folder supports user defined flags.
      * @param value The value of 'isUser'.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder setIsUser(java.lang.Boolean value) {
      validate(fields()[6], value);
      this.isUser = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'isUser' field has been set.
      * A special flag that indicates that this folder supports user defined flags.
      * @return True if the 'isUser' field has been set, false otherwise.
      */
    public boolean hasIsUser() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'isUser' field.
      * A special flag that indicates that this folder supports user defined flags.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder clearIsUser() {
      isUser = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'isExpunged' field.
      * Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked "expunged" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder.
      * @return The value.
      */
    public java.lang.Boolean getIsExpunged() {
      return isExpunged;
    }


    /**
      * Sets the value of the 'isExpunged' field.
      * Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked "expunged" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder.
      * @param value The value of 'isExpunged'.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder setIsExpunged(java.lang.Boolean value) {
      validate(fields()[7], value);
      this.isExpunged = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'isExpunged' field has been set.
      * Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked "expunged" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder.
      * @return True if the 'isExpunged' field has been set, false otherwise.
      */
    public boolean hasIsExpunged() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'isExpunged' field.
      * Messages that are expunged due to an explict expunge() request on the containing Folder are removed from the Folder immediately. Messages that are externally expunged by another source are marked "expunged" and return true for the isExpunged() method, but they are not removed from the Folder until an explicit expunge() is done on the Folder.
      * @return This builder.
      */
    public com.atomic.document.MailMetadata.Builder clearIsExpunged() {
      isExpunged = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MailMetadata build() {
      try {
        MailMetadata record = new MailMetadata();
        record.folderName = fieldSetFlags()[0] ? this.folderName : (java.lang.String) defaultValue(fields()[0]);
        record.isAnswered = fieldSetFlags()[1] ? this.isAnswered : (java.lang.Boolean) defaultValue(fields()[1]);
        record.isDeleted = fieldSetFlags()[2] ? this.isDeleted : (java.lang.Boolean) defaultValue(fields()[2]);
        record.isDraft = fieldSetFlags()[3] ? this.isDraft : (java.lang.Boolean) defaultValue(fields()[3]);
        record.isRecent = fieldSetFlags()[4] ? this.isRecent : (java.lang.Boolean) defaultValue(fields()[4]);
        record.isSeen = fieldSetFlags()[5] ? this.isSeen : (java.lang.Boolean) defaultValue(fields()[5]);
        record.isUser = fieldSetFlags()[6] ? this.isUser : (java.lang.Boolean) defaultValue(fields()[6]);
        record.isExpunged = fieldSetFlags()[7] ? this.isExpunged : (java.lang.Boolean) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<MailMetadata>
    WRITER$ = (org.apache.avro.io.DatumWriter<MailMetadata>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<MailMetadata>
    READER$ = (org.apache.avro.io.DatumReader<MailMetadata>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.folderName == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.folderName);
    }

    if (this.isAnswered == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeBoolean(this.isAnswered);
    }

    if (this.isDeleted == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeBoolean(this.isDeleted);
    }

    if (this.isDraft == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeBoolean(this.isDraft);
    }

    if (this.isRecent == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeBoolean(this.isRecent);
    }

    if (this.isSeen == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeBoolean(this.isSeen);
    }

    if (this.isUser == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeBoolean(this.isUser);
    }

    if (this.isExpunged == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeBoolean(this.isExpunged);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.folderName = null;
      } else {
        this.folderName = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.isAnswered = null;
      } else {
        this.isAnswered = in.readBoolean();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.isDeleted = null;
      } else {
        this.isDeleted = in.readBoolean();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.isDraft = null;
      } else {
        this.isDraft = in.readBoolean();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.isRecent = null;
      } else {
        this.isRecent = in.readBoolean();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.isSeen = null;
      } else {
        this.isSeen = in.readBoolean();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.isUser = null;
      } else {
        this.isUser = in.readBoolean();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.isExpunged = null;
      } else {
        this.isExpunged = in.readBoolean();
      }

    } else {
      for (int i = 0; i < 8; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.folderName = null;
          } else {
            this.folderName = in.readString();
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.isAnswered = null;
          } else {
            this.isAnswered = in.readBoolean();
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.isDeleted = null;
          } else {
            this.isDeleted = in.readBoolean();
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.isDraft = null;
          } else {
            this.isDraft = in.readBoolean();
          }
          break;

        case 4:
          if (in.readIndex() != 1) {
            in.readNull();
            this.isRecent = null;
          } else {
            this.isRecent = in.readBoolean();
          }
          break;

        case 5:
          if (in.readIndex() != 1) {
            in.readNull();
            this.isSeen = null;
          } else {
            this.isSeen = in.readBoolean();
          }
          break;

        case 6:
          if (in.readIndex() != 1) {
            in.readNull();
            this.isUser = null;
          } else {
            this.isUser = in.readBoolean();
          }
          break;

        case 7:
          if (in.readIndex() != 1) {
            in.readNull();
            this.isExpunged = null;
          } else {
            this.isExpunged = in.readBoolean();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}











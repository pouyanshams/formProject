// Update FormDTO.java
public class FormDTO {
    private Long id;
    private String name;
    private boolean published;
    private List<FieldDTO> fields;
    private SubmitEndpointDTO submitEndpoint;

    // Default constructor
    public FormDTO() {}

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public boolean isPublished() { return published; }
    public List<FieldDTO> getFields() { return fields; }
    public SubmitEndpointDTO getSubmitEndpoint() { return submitEndpoint; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPublished(boolean published) { this.published = published; }
    public void setFields(List<FieldDTO> fields) { this.fields = fields; }
    public void setSubmitEndpoint(SubmitEndpointDTO submitEndpoint) { this.submitEndpoint = submitEndpoint; }
}
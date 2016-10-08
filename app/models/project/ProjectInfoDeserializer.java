/**
 * A custom deserializer used to deserialize json to a {@link ProjectInfo} object.
 *
 * @author mhooda
 */

package models;


import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import models.ProjectInfo;


import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import services.UserDAO;
import utils.DAOUtils;
import utils.commonUtils;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PrePersist;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.BasicWatchers;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


import java.io.IOException;
import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class for project info
 * 
 * @author mhooda
 * 
 */

public class ProjectInfoDeserializer extends StdDeserializer<ProjectInfo> implements ResolvableDeserializer
{
    /**
     * The default {@link JsonDeserializer}, created by Jackson, that we will delegate to for deserialization of the json.
     */
    private final JsonDeserializer<?> defaultJsonDeserializer;

    /**
     * The {@link ObjectMapper} used to create tree data.
     */
    private final ObjectMapper objectMapper;

    /**
     * The json path to the data that maps to the {@link NestedCarModel} object.
     */
    private final String[] modelTraversalPath;

    /**
     * The {@link JsonFactory} used to create {@link JsonParser} instances.
     */
    private final JsonFactory jsonFactory;

    /**
     *
     * @param defaultJsonDeserializer
     *          The {@link JsonDeserializer} that will be used to delegate deserialization to
     * @param objectMapper
     *          Used to create tree data
     * @param modelTraversalPath
     *          The json path to the data that maps to the {@link ProjectInfo} object.
     */
    public ProjectInfoDeserializer(JsonDeserializer<?> defaultJsonDeserializer, ObjectMapper objectMapper, String[] modelTraversalPath) {
        super(ProjectInfo.class);

        this.defaultJsonDeserializer = defaultJsonDeserializer;
        this.objectMapper = objectMapper;
        this.modelTraversalPath = modelTraversalPath;
        this.jsonFactory = new JsonFactory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) 
											throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        // Iterate down the json tree until we get to the content portion...the portion that maps to the ProjectInfo
        for(String nodePath : modelTraversalPath) {
            node = node.findValue(nodePath);
            if(node == null) {
                throw new IllegalStateException("Unexpected json traversal path format!");
            }
        }
        // Close the original parser since we don't need it anymore
        jsonParser.close();

        String treeString = objectMapper.writeValueAsString(objectMapper.treeToValue(node, Object.class));
        // Create a new parser where the root of the json is the NestedCarModel portion
        JsonParser newParser = jsonFactory.createParser(treeString);
        // Queue the first token for the deserialization (NOTE: this is important--you get a NullPointerException without it)
        newParser.nextToken();

        return (ProjectInfo) defaultJsonDeserializer.deserialize(newParser, deserializationContext);
    }

    /**
     * {@inheritDoc}
     */
    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        ((ResolvableDeserializer) defaultJsonDeserializer).resolve(deserializationContext);
    }
}
  

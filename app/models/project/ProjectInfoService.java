/**
 * A service that can be instantiated to (de)serialize {@link ProjectInfo} objects to and from json.
 *
 * @author mhooda
 */
package models;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import models.ProjectInfoDeserializer;
import models.ProjectInfoSerializer;
import models.ProjectInfo;

import java.io.IOException;

public class ProjectInfoService {

    /**
     * The json path to the data that maps to the {@link ProjectInfo} object.
     */
    private final String[] modelTraversalPath;

    private ObjectMapper objectMapper;

    private JsonFactory jsonFactory;

    public ProjectInfoService(String[] modelTraversalPath) {
        this.modelTraversalPath = modelTraversalPath;
        jsonFactory = new JsonFactory();

        initObjectMapper();
    }

    /**
     * Initializes the {@link ObjectMapper} used by the service. This includes the registration of custom
     * (de)serializers.
     */
    private void initObjectMapper() {
        objectMapper = new ObjectMapper();

        // Registers the custom deserializer
        objectMapper.registerModule(new DeserializerModule(new DeserializerModifier()));
        // Register the custom serializer
        objectMapper.registerModule(new SerializerModule(new SerializerModifier()));
    }

    /**
     * Deserializes the parameter json to a {@link ProjectInfo} object.
     *
     * @param ProjectInfoJson
     *         The json {@link String} to deserialize.
     * @return The {@link ProjectInfo} object that represents the parameter json String
     * @throws IOException
     *         If the Jackson object mapper is unable to deserialize the json string
     */
    public ProjectInfo deserialize(String ProjectInfoJson) throws IOException {
        return objectMapper.readValue(ProjectInfoJson, ProjectInfo.class);
    }

    /**
     * Serializes the parameter ProjectInfo to a json string.
     *
     * @param ProjectInfo
     *         The {@link ProjectInfo} object to serialize
     * @return A json string that represents the paramter ProjectInfo
     * @throws IOException
     *         If the Jackson object mapper is unable to serialize the model
     */
    public String serialize(ProjectInfo ProjectInfo) throws IOException {
        return objectMapper.writeValueAsString(ProjectInfo);
    }

    /**
     * A Jackson {@link SimpleModule} used to register a customized deserializer.
     *
     * @author mhooda
     */
    private static final class DeserializerModule extends SimpleModule {

        private BeanDeserializerModifier deserializerModifier;

        public DeserializerModule(BeanDeserializerModifier deserializerModifier) {
            super("DeserializerModule", Version.unknownVersion());
            this.deserializerModifier = deserializerModifier;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setupModule(SetupContext context) {
            super.setupModule(context);
            context.addBeanDeserializerModifier(deserializerModifier);
        }

    }

    /**
     * A Jackson {@link BeanDeserializerModifier} instance used to define the {@link ProjectInfoDeserializer} as the
     * deserializer for a {@link ProjectInfo} instance. Note that the {@link ProjectInfoDeserializer} is only used
     * when the bean being deserialized is a {@link ProjectInfo}.
     *
     * @author mhooda
     */
    private final class DeserializerModifier extends BeanDeserializerModifier {

        /**
         * {@inheritDoc}
         */
        @Override
        public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
            // If the bean being deserialized is a ProjectInfo, use the customer deserializer
            if (ProjectInfo.class.equals(beanDesc.getBeanClass())) {
                return new ProjectInfoDeserializer(deserializer, objectMapper, modelTraversalPath);
            }

            return super.modifyDeserializer(config, beanDesc, deserializer);
        }

    }

    /**
     * A Jackson {@link SimpleModule} used to register a customized serializer.
     *
     * @author mhooda
     */
    private static final class SerializerModule extends SimpleModule {

        private BeanSerializerModifier serializerModifier;

        public SerializerModule(BeanSerializerModifier serializerModifier) {
            super("SerializerModule", Version.unknownVersion());
            this.serializerModifier = serializerModifier;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setupModule(SetupContext context) {
            super.setupModule(context);
            context.addBeanSerializerModifier(serializerModifier);
        }

    }

    /**
     * A Jackson {@link BeanSerializerModifier} instance used to define the {@link ProjectInfoSerializer} as the
     * serializer for a {@link ProjectInfo} instance. Note that the {@link ProjectInfoSerializer} is only used
     * when the bean being serialized is a {@link ProjectInfo}.
     *
     * @author mhooda
     */
    private final class SerializerModifier extends BeanSerializerModifier {

        @Override
        public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
            // If the bean being serialized is a ProjectInfo, use the customer serializer
            if(ProjectInfo.class.equals(beanDesc.getBeanClass())) {
                return new ProjectInfoSerializer(jsonFactory, (JsonSerializer<Object>) serializer, modelTraversalPath);
            }

            return super.modifySerializer(config, beanDesc, serializer);
        }
    }

}

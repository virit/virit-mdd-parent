package cn.virit.graphql;

import cn.virit.domain.base.service.ModelService;
import cn.virit.metadata.constants.FieldType;
import cn.virit.metadata.model.ModelInfo;
import cn.virit.metadata.utils.IOUtils;
import cn.virit.metadata.utils.VelocityUtils;
import graphql.GraphQL;
import graphql.language.*;
import graphql.schema.*;
import graphql.schema.idl.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GraphQLProvider
 *
 * @author Virit
 * @since 1.0
 */
@Component
public class GraphQLProvider {

    private static String sdlPath;
    private final DataSourceScriptDatabaseInitializer initializer;
    private final ModelService modelService;
    private final GraphQLDataFetcher graphQLDataFetcher;
    private GraphQL graphQL;

    public GraphQLProvider(ModelService modelService, GraphQLDataFetcher graphQLDataFetcher, DataSourceScriptDatabaseInitializer initializer) {
        this.modelService = modelService;
        this.graphQLDataFetcher = graphQLDataFetcher;
        this.initializer = initializer;
    }

    public static String getSdlPath() {
        return sdlPath;
    }

    @Bean
    public GraphQL graphQL() throws IOException {
        init();
        return graphQL;
    }

    public void init() throws IOException {
        var in = GraphQLProvider.class.getClassLoader().getResourceAsStream("templates/gql-schema.vm");
        var models = modelService.getAllModelInfo();
        var context = new VelocityUtils.VelocityContextBuilder()
            .set("models", models)
            .set("provider", this)
            .build();
        var sdlFile = new File("./graphql_schema.sdl");
        sdlPath = sdlFile.getAbsolutePath();
        VelocityUtils.render(IOUtils.loadAsString(in), context, new PrintWriter(new FileWriter(sdlFile)));
        GraphQLSchema graphQLSchema = buildSchema(new FileInputStream(sdlFile), models);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(InputStream sdl, List<ModelInfo> models) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring(models);
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring(List<ModelInfo> models) {
        var builder = RuntimeWiring.newRuntimeWiring();
        builder.scalar(GraphQLScalarType.newScalar()
            .name("ConditionValue")
            .description("ConditionValue Type")
            .coercing(new ConditionValueCoercing())
            .build());
        var queryTypeBuilder = TypeRuntimeWiring.newTypeWiring("Query");
        for (var model : models) {
            queryTypeBuilder.dataFetcher(model.getName(), graphQLDataFetcher);
        }
        builder.type(queryTypeBuilder);

        for (var model : models) {

            var referFields = model.getFields().values()
                .stream()
                .filter(it -> it.getType() == FieldType.OBJECT)
                .collect(Collectors.toList());
            if (!referFields.isEmpty() || model.getChildren() != null) {
                var fieldTypeBuilder = TypeRuntimeWiring.newTypeWiring(model.getName());
                for (var referField : referFields) {
                    fieldTypeBuilder.dataFetcher(referField.getName(), graphQLDataFetcher);
                }
                if (model.getChildren() != null) {
                    for (var child : model.getChildren().values()) {
                        fieldTypeBuilder.dataFetcher(child.getName(), graphQLDataFetcher);
                    }
                }
                builder.type(fieldTypeBuilder);
            }
        }
        return builder.build();
    }

    public String mapType(Field field) {

        String typeName;
        switch (field.getType()) {
            case INTEGER:
                typeName = "Int";
                break;
            case OBJECT:
                typeName = (String) field.getProperties().get("objectType");
                break;
            default:
                typeName = "String";
                break;
        }
        return typeName;
    }

    public static class ConditionValueCoercing implements Coercing<Object, String> {

        @Override
        public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
            return String.valueOf(dataFetcherResult);
        }

        @Override
        public @NotNull
        Object parseValue(@NotNull Object input) throws CoercingParseValueException {
            return input;
        }

        @Override
        public @NotNull
        Object parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {

            if (input instanceof StringValue) {
                return ((StringValue) input).getValue();
            } else if (input instanceof BooleanValue) {
                return ((BooleanValue) input).isValue();
            } else if (input instanceof IntValue) {
                return ((IntValue) input).getValue();
            } else if (input instanceof FloatValue) {
                return ((FloatValue) input).getValue();
            }
            return input.toString();
        }
    }
}
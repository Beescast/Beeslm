package projects.commons.utils.config;

import java.lang.annotation.*;

/**
 * This annotation is used to mark field that should be processed by
 * {@link com.games.commons.configuration.ConfigurableProcessor}<br>
 * <br>
 * <p/>
 * This annotation is Documented, all definitions with it will appear in javadoc
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
    /**
     * This string shows to {@link com.games.commons.configuration.ConfigurableProcessor} that init value of the
     * object should not be overriden.
     */
    public static final String DEFAULT_VALUE = "DO_NOT_OVERWRITE_INITIALIAZION_VALUE";

    /**
     * Property name in configuration
     *
     * @return name of the property that will be used
     */
    public String key();

    /**
     * PropertyTransformer to use.<br>
     * List of automaticly transformed types:<br>
     * <ul>
     * <li>{@link Boolean} and boolean by {@link com.games.commons.configuration.transformers.BooleanTransformer}</li>
     * <li>{@link Byte} and byte by {@link com.games.commons.configuration.transformers.ByteTransformer}</li>
     * <li>{@link Character} and char by {@link com.games.commons.configuration.transformers.CharTransformer}</li>
     * <li>{@link Short} and short by {@link com.games.commons.configuration.transformers.ShortTransformer}</li>
     * <li>{@link Integer} and int by {@link com.games.commons.configuration.transformers.IntegerTransformer}</li>
     * <li>{@link Float} and float by {@link com.games.commons.configuration.transformers.FloatTransformer}</li>
     * <li>{@link Long} and long by {@link com.games.commons.configuration.transformers.LongTransformer}</li>
     * <li>{@link Double} and double by {@link com.games.commons.configuration.transformers.DoubleTransformer}</li>
     * <li>{@link String} by {@link com.games.commons.configuration.transformers.StringTransformer}</li>
     * <li>{@link Enum} and enum by {@link com.games.commons.configuration.transformers.EnumTransformer}</li>
     * <li>{@link java.io.File} by {@link com.games.commons.configuration.transformers.FileTransformer}</li>
     * <li>{@link java.net.InetSocketAddress} by
     * {@link com.games.commons.configuration.transformers.InetSocketAddressTransformer}</li>
     * <li>{@link java.util.regex.Pattern} by {@link com.games.commons.configuration.transformers.PatternTransformer}
     * </ul>
     * <p/>
     * If your value is one of this types - just leave this field empty
     *
     * @return returns class that will be used to transform value
     */
    @SuppressWarnings("rawtypes")
    public Class<? extends PropertyTransformer> propertyTransformer() default PropertyTransformer.class;

    /**
     * Represents default value that will be parsed if key not found. If this key equals(default) {@link #DEFAULT_VALUE}
     * init value of the object won't be overriden
     *
     * @return default value of the property
     */
    public String defaultValue() default DEFAULT_VALUE;
}

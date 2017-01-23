import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.telegram.api.TLApiContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 26 of September of 2016
 */

public class TestValidateSchema {
    private static List<Integer> tlObjects;
    private static List<Integer> tlSecretObjects;

    private TLApiContext context;

    @BeforeClass
    public static void setUpClass() {
        tlObjects = new ArrayList<>();
        tlSecretObjects = new ArrayList<>();
        try {
            InputStream is = TestValidateSchema.class.getResourceAsStream("schema.json");
            String jsonTxt = IOUtils.toString(is);
            JSONObject json = new JSONObject(jsonTxt);
            JSONArray constructors = json.getJSONArray("constructors");
            for (int i = 0; i < constructors.length(); i++) {
                tlObjects.add(constructors.getJSONObject(i).getInt("id"));
            }
            JSONArray methods = json.getJSONArray("methods");
            for (int i = 0; i < methods.length(); i++) {
                tlObjects.add(methods.getJSONObject(i).getInt("id"));
            }
            is = TestValidateSchema.class.getResourceAsStream("secretschema.json");
            jsonTxt = IOUtils.toString(is);
            json = new JSONObject(jsonTxt);
            constructors = json.getJSONArray("constructors");
            for (int i = 0; i < constructors.length(); i++) {
                tlSecretObjects.add(constructors.getJSONObject(i).getInt("id"));
            }
            methods = json.getJSONArray("methods");
            for (int i = 0; i < methods.length(); i++) {
                tlSecretObjects.add(methods.getJSONObject(i).getInt("id"));
            }
        } catch (IOException e) {
            Assert.fail("Failed to find resource file");
        }
    }

    @Before
    public void setUp() {
        context = new TLApiContext();
    }

    @Test
    public void TestShemaIsLoaded() {
        Assert.assertFalse("Failed to load schema", tlObjects.isEmpty());
    }

    @Test
    public void TestSecretShemaIsLoaded() {
        Assert.assertFalse("Failed to load schema", tlSecretObjects.isEmpty());
    }

    @Test
    public void TestAllObjectsAndMethods() {
        for (Integer id : tlObjects) {
            Assert.assertTrue("Failed to load object " + id + "(" + Integer.toHexString(id) + ")",
                    context.isSupportedObject(id));
        }
    }

    @Test
    public void TestAllObjectsAndMethodsAreInSchema() {
        List<Integer> objects = new ArrayList<>();
        objects.addAll(tlObjects);
        objects.addAll(tlSecretObjects);
        for (Integer id : context.getRegisteredClassIds()) {
            Assert.assertTrue("Failed to load find " + id + "(" + Integer.toHexString(id) + ")",
                    objects.contains(id));
        }
    }
}

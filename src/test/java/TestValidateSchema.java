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

    private TLApiContext context;

    @BeforeClass
    public static void setUpClass() {
        tlObjects = new ArrayList<>();
        try {
            InputStream is = TestValidateSchema.class.getResourceAsStream("schema.json");
            String jsonTxt = IOUtils.toString(is);
            JSONObject json = new JSONObject(jsonTxt);
            JSONArray constructors = json.getJSONArray("constructors");
            for (int i = 0; i < constructors.length(); i++) {
                tlObjects.add(constructors.getJSONObject(i).getInt("id"));
            }
            JSONArray methods = json.getJSONArray("constructors");
            for (int i = 0; i < methods.length(); i++) {
                tlObjects.add(methods.getJSONObject(i).getInt("id"));
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
    public void TestAllObjectsAndMethods() {
        for (Integer id : tlObjects) {
            Assert.assertTrue("Failed to load object " + id + "(" + Integer.toHexString(id) + ")",
                    context.isSupportedObject(id));
        }
    }
}

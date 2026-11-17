package com.weido.create_bb.data.compat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.fabricators_of_create.porting_lib.models.geometry.IGeometryLoader;
import io.github.fabricators_of_create.porting_lib.models.obj.ObjLoader;
import io.github.fabricators_of_create.porting_lib.models.obj.ObjModel;
import net.minecraft.util.GsonHelper;

public class ForgeObjLoader implements IGeometryLoader<ObjModel> {
    public static final ForgeObjLoader INSTANCE = new ForgeObjLoader();

    @Override
    public ObjModel read(JsonObject json, JsonDeserializationContext context) throws JsonParseException {
        JsonObject normalized = json.deepCopy();
        if (normalized.has("mtl_override") && !normalized.has("mtlOverride")) {
            normalized.addProperty("mtlOverride", GsonHelper.getAsString(normalized, "mtl_override"));
        }
        if (normalized.has("flip_v") && !normalized.has("flipV")) {
            normalized.addProperty("flipV", GsonHelper.getAsBoolean(normalized, "flip_v", true));
        }
        return ObjLoader.INSTANCE.read(normalized, context);
    }
}

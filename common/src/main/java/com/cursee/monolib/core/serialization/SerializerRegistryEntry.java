package com.cursee.monolib.core.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.core.Registry;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

/** Adapted from Darkhax's <a href="https://github.com/Darkhax-Minecraft/Bookshelf">Bookshelf</a>. */
public class SerializerRegistryEntry<T> implements ISerializer<T> {

    private final Registry<T> registry;

    public SerializerRegistryEntry(Registry<T> registry) {

        this.registry = registry;
    }

    @Override
    public T fromJSON(JsonElement json) {

        final ResourceLocation id = Serializers.RESOURCE_LOCATION.fromJSON(json);

        if (registry.containsKey(id)) {
            return registry.get(id);
        }

        throw new JsonParseException("ID '" + id + "' has not been registered");
    }

    @Override
    public JsonElement toJSON(T toWrite) {

        return Serializers.RESOURCE_LOCATION.toJSON(registry.getKey(toWrite));
    }

    @Override
    public T fromByteBuf(FriendlyByteBuf buffer) {

        final ResourceLocation id = Serializers.RESOURCE_LOCATION.fromByteBuf(buffer);
        return registry.get(id);
    }

    @Override
    public void toByteBuf(FriendlyByteBuf buffer, T toWrite) {

        Serializers.RESOURCE_LOCATION.toByteBuf(buffer, registry.getKey(toWrite));
    }

    @Override
    public Tag toNBT(T toWrite) {

        return Serializers.RESOURCE_LOCATION.toNBT(registry.getKey(toWrite));
    }

    @Override
    public T fromNBT(Tag nbt) {

        return registry.get(Serializers.RESOURCE_LOCATION.fromNBT(nbt));
    }
}

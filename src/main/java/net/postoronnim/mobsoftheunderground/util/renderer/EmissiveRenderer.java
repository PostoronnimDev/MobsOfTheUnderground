package net.postoronnim.mobsoftheunderground.util.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.util.feature.GlowLayer;

public class EmissiveRenderer<E extends MobEntity, S extends LivingEntityRenderState, M extends EntityModel<S>> extends MobEntityRenderer<E, S, M> {
    private final String path;
    private final String emissive;

    public EmissiveRenderer(EntityRendererFactory.Context context, M entityModel, float f, String path, String emissive) {
        super(context, entityModel, f);
        this.path = path;
        this.emissive = emissive;
        this.addFeature(new GlowLayer<>(this, Identifier.of(MobsOfTheUnderground.MOD_ID, this.emissive)));
    }

    @Override
    public Identifier getTexture(S state) {
        return Identifier.of(MobsOfTheUnderground.MOD_ID, path);
    }

    @Override
    public S createRenderState() {
        return null;
    }
}

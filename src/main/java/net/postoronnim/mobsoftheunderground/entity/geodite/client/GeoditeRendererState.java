package net.postoronnim.mobsoftheunderground.entity.geodite.client;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class GeoditeRendererState extends LivingEntityRenderState {
    public AnimationState idleAnimationState = new AnimationState();
    public AnimationState attackAnimationState = new AnimationState();
}

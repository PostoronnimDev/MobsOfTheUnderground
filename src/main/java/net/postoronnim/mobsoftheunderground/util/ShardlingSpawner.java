package net.postoronnim.mobsoftheunderground.util;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.postoronnim.mobsoftheunderground.entity.ModEntities;
import net.postoronnim.mobsoftheunderground.entity.shardling.custom.ShardlingEntity;
import net.postoronnim.mobsoftheunderground.particle.ModParticles;

public class ShardlingSpawner {
    public static void spawnShardlings(Entity entity, Vec3d pos, int number) {
        if(entity.getWorld() instanceof ServerWorld serverWorld) {
            Random random = serverWorld.getRandom();
            for (int i = 0; i < number; i++) {
                ShardlingEntity shardling = new ShardlingEntity(ModEntities.SHARDLING, serverWorld);
                Vec3d newPos = new Vec3d(pos.x + random.nextDouble() - 0.5d, pos.y + 0.5d, pos.z + random.nextDouble() - 0.5d);
                spawnParticleExplosion(entity, newPos, 10);
                shardling.setPos(newPos.x, newPos.y, newPos.z);
                serverWorld.spawnEntity(shardling);
                shardling.addVelocityInternal(new Vec3d(random.nextDouble() - 0.5d, 0.3d, random.nextDouble() - 0.5d));
            }
        }
    }

    public static void spawnParticleExplosion(Entity entity, Vec3d pos, int number) {
        if(entity.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.playSound(entity, entity.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.HOSTILE, 1f, 1f);
            serverWorld.playSound(entity, entity.getBlockPos(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.HOSTILE, 1f, 1f);
            serverWorld.spawnParticles(ModParticles.AMETHYST_PARTICLE, pos.x, pos.y, pos.z, number, 0.5d, 0.5d, 0.5d, 1.5d);
        }
    }
}

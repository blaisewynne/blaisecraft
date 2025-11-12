package com.blaisecraft.entity.client.animatedarmour;

import com.blaisecraft.BlaiseCraft;
import com.blaisecraft.entity.client.beholder.BeholderModel;
import com.blaisecraft.entity.client.beholder.BeholderRenderState;
import com.blaisecraft.entity.custom.AnimatedArmourEntity;
import com.blaisecraft.entity.custom.BeholderEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public abstract class AnimatedArmourBaseRenderer<T extends AnimatedArmourEntity, S extends AnimatedArmourRenderState, M extends AnimatedArmourModel<S>>
        extends BipedEntityRenderer<T, S, M> {
    private static final Identifier TEXTURE = Identifier.of(BlaiseCraft.MOD_ID, "textures/entity/animated_armour/animated_armour.png");

    protected AnimatedArmourBaseRenderer(
            EntityRendererFactory.Context context, M mainModel, M babyMainModel, EquipmentModelData<M> equipmentModelData, EquipmentModelData<M> equipmentModelData2
    ) {
        super(context, mainModel, babyMainModel, 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(this, equipmentModelData, equipmentModelData2, context.getEquipmentRenderer()));
    }

    public AnimatedArmourBaseRenderer(EntityRendererFactory.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    public Identifier getTexture(S zombieEntityRenderState) {
        return TEXTURE;
    }

    public void updateRenderState(T beholderEntity, S beholderRenderState, float f) {
        super.updateRenderState(beholderEntity, beholderRenderState, f);
        beholderRenderState.attacking = beholderEntity.isAttacking();
    }
}
package com.blaisecraft.entity.client.animatedarmour;

import com.blaisecraft.entity.client.beholder.BeholderBaseRenderer;
import com.blaisecraft.entity.client.beholder.BeholderModel;
import com.blaisecraft.entity.client.beholder.BeholderRenderState;
import com.blaisecraft.entity.custom.AnimatedArmourEntity;
import com.blaisecraft.entity.custom.BeholderEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;

@Environment(EnvType.CLIENT)
public class AnimatedArmourRenderer extends AnimatedArmourBaseRenderer<AnimatedArmourEntity, AnimatedArmourRenderState, AnimatedArmourModel<AnimatedArmourRenderState>> {
    public AnimatedArmourRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.ZOMBIE, EntityModelLayers.ZOMBIE_BABY, EntityModelLayers.ZOMBIE_EQUIPMENT, EntityModelLayers.ZOMBIE_BABY_EQUIPMENT);
    }

    public AnimatedArmourRenderState createRenderState() {
        return new AnimatedArmourRenderState();
    }

    public AnimatedArmourRenderer(
            EntityRendererFactory.Context ctx,
            EntityModelLayer layer,
            EntityModelLayer legsArmorLayer,
            EquipmentModelData<EntityModelLayer> equipmentModelData,
            EquipmentModelData<EntityModelLayer> equipmentModelData2
    ) {
        super(
                ctx,
                new AnimatedArmourModel<>(ctx.getPart(layer)),
                new AnimatedArmourModel<>(ctx.getPart(legsArmorLayer)),
                EquipmentModelData.mapToEntityModel(equipmentModelData, ctx.getEntityModels(), AnimatedArmourModel::new),
                EquipmentModelData.mapToEntityModel(equipmentModelData2, ctx.getEntityModels(), AnimatedArmourModel::new)
        );
    }
}
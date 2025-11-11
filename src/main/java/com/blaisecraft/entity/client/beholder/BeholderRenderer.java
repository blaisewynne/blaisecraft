package com.blaisecraft.entity.client.beholder;

import com.blaisecraft.entity.custom.BeholderEntity;
import com.blaisecraft.entity.custom.VampireEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;

@Environment(EnvType.CLIENT)
public class BeholderRenderer extends BeholderBaseRenderer<BeholderEntity, BeholderRenderState, BeholderModel<BeholderRenderState>> {
    public BeholderRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.ZOMBIE, EntityModelLayers.ZOMBIE_BABY, EntityModelLayers.ZOMBIE_EQUIPMENT, EntityModelLayers.ZOMBIE_BABY_EQUIPMENT);
    }

    public BeholderRenderState createRenderState() {
        return new BeholderRenderState();
    }

    public BeholderRenderer(
            EntityRendererFactory.Context ctx,
            EntityModelLayer layer,
            EntityModelLayer legsArmorLayer,
            EquipmentModelData<EntityModelLayer> equipmentModelData,
            EquipmentModelData<EntityModelLayer> equipmentModelData2
    ) {
        super(
                ctx,
                new BeholderModel<>(ctx.getPart(layer)),
                new BeholderModel<>(ctx.getPart(legsArmorLayer)),
                EquipmentModelData.mapToEntityModel(equipmentModelData, ctx.getEntityModels(), BeholderModel::new),
                EquipmentModelData.mapToEntityModel(equipmentModelData2, ctx.getEntityModels(), BeholderModel::new)
        );
    }
}
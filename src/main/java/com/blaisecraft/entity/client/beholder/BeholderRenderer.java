package com.blaisecraft.entity.client.vampire;

import com.blaisecraft.entity.custom.VampireEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;

@Environment(EnvType.CLIENT)
public class VampireRenderer extends VampireBaseRenderer<VampireEntity, VampireRenderState, VampireModel<VampireRenderState>> {
    public VampireRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.ZOMBIE, EntityModelLayers.ZOMBIE_BABY, EntityModelLayers.ZOMBIE_EQUIPMENT, EntityModelLayers.ZOMBIE_BABY_EQUIPMENT);
    }

    public VampireRenderState createRenderState() {
        return new VampireRenderState();
    }

    public VampireRenderer(
            EntityRendererFactory.Context ctx,
            EntityModelLayer layer,
            EntityModelLayer legsArmorLayer,
            EquipmentModelData<EntityModelLayer> equipmentModelData,
            EquipmentModelData<EntityModelLayer> equipmentModelData2
    ) {
        super(
                ctx,
                new VampireModel<>(ctx.getPart(layer)),
                new VampireModel<>(ctx.getPart(legsArmorLayer)),
                EquipmentModelData.mapToEntityModel(equipmentModelData, ctx.getEntityModels(), VampireModel::new),
                EquipmentModelData.mapToEntityModel(equipmentModelData2, ctx.getEntityModels(), VampireModel::new)
        );
    }
}
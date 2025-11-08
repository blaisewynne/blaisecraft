package com.blaisecraft.entity.client.werewolf;

import com.blaisecraft.entity.custom.WerewolfEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;

@Environment(EnvType.CLIENT)
public class WerewolfRenderer extends WerewolfBaseRenderer<WerewolfEntity, WerewolfRenderState, WerewolfModel<WerewolfRenderState>> {
    public WerewolfRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.ZOMBIE, EntityModelLayers.ZOMBIE_BABY, EntityModelLayers.ZOMBIE_EQUIPMENT, EntityModelLayers.ZOMBIE_BABY_EQUIPMENT);
    }

    public WerewolfRenderState createRenderState() {
        return new WerewolfRenderState();
    }

    public WerewolfRenderer(
            EntityRendererFactory.Context ctx,
            EntityModelLayer layer,
            EntityModelLayer legsArmorLayer,
            EquipmentModelData<EntityModelLayer> equipmentModelData,
            EquipmentModelData<EntityModelLayer> equipmentModelData2
    ) {
        super(
                ctx,
                new WerewolfModel<>(ctx.getPart(layer)),
                new WerewolfModel<>(ctx.getPart(legsArmorLayer)),
                EquipmentModelData.mapToEntityModel(equipmentModelData, ctx.getEntityModels(), WerewolfModel::new),
                EquipmentModelData.mapToEntityModel(equipmentModelData2, ctx.getEntityModels(), WerewolfModel::new)
        );
    }
}

package com.blaisecraft.entity.client.vampire;

import com.blaisecraft.BlaiseCraft;
import com.blaisecraft.entity.custom.VampireEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public abstract class VampireBaseRenderer<T extends VampireEntity, S extends VampireRenderState, M extends VampireModel<S>>
        extends BipedEntityRenderer<T, S, M> {
    private static final Identifier TEXTURE = Identifier.of(BlaiseCraft.MOD_ID, "textures/entity/vampire/vampire.png");

    protected VampireBaseRenderer(
            EntityRendererFactory.Context context, M mainModel, M babyMainModel, EquipmentModelData<M> equipmentModelData, EquipmentModelData<M> equipmentModelData2
    ) {
        super(context, mainModel, babyMainModel, 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(this, equipmentModelData, equipmentModelData2, context.getEquipmentRenderer()));
    }

    public VampireBaseRenderer(EntityRendererFactory.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    public Identifier getTexture(S vampireEntityRenderState) {
        return TEXTURE;
    }

    public void updateRenderState(T vampireEntity, S vampireRenderState, float f) {
        super.updateRenderState(vampireEntity, vampireRenderState, f);
        vampireRenderState.attacking = vampireEntity.isAttacking();
    }
}

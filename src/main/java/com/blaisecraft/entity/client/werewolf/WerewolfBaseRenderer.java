package com.blaisecraft.entity.client.werewolf;

import com.blaisecraft.BlaiseCraft;
import com.blaisecraft.entity.custom.WerewolfEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public abstract class WerewolfBaseRenderer<T extends WerewolfEntity, S extends WerewolfRenderState, M extends WerewolfModel<S>>
        extends BipedEntityRenderer<T, S, M> {
    private static final Identifier TEXTURE = Identifier.of(BlaiseCraft.MOD_ID, "textures/entity/werewolf/werewolf.png");

    protected WerewolfBaseRenderer(
            EntityRendererFactory.Context context, M mainModel, M babyMainModel, EquipmentModelData<M> equipmentModelData, EquipmentModelData<M> equipmentModelData2
    ) {
        super(context, mainModel, babyMainModel, 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(this, equipmentModelData, equipmentModelData2, context.getEquipmentRenderer()));
    }

    public WerewolfBaseRenderer(EntityRendererFactory.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    public Identifier getTexture(S werewolfEntityRenderState) {
        return TEXTURE;
    }

    public void updateRenderState(T werewolfEntity, S werewolfRenderState, float f) {
        super.updateRenderState(werewolfEntity, werewolfRenderState, f);
        werewolfRenderState.attacking = werewolfEntity.isAttacking();
    }
}

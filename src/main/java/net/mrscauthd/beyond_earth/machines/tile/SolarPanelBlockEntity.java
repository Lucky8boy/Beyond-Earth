package net.mrscauthd.beyond_earth.machines.tile;

import java.util.Arrays;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.IEnergyStorage;
import net.mrscauthd.beyond_earth.capabilities.energy.EnergyStorageBasic;
import net.mrscauthd.beyond_earth.config.Config;
import net.mrscauthd.beyond_earth.guis.screens.solarpanel.SolarPanelGui;
import net.mrscauthd.beyond_earth.registries.BlockEntitiesRegistry;

public class SolarPanelBlockEntity extends GeneratorBlockEntity {

	public static final int DEFAULT_ENERGY_USAGE = 5;

	public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntitiesRegistry.SOLAR_PANEL_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new SolarPanelGui.GuiContainer(id, inventory, this);
	}

	protected int getGenerationInTick() {
		return this.getMaxGeneration();
	}

	@Override
	public int getMaxGeneration() {
		return Config.SOLAR_PANEL_ENERGY_GENERATION.get();
	}

	@Override
	protected boolean canGenerateEnergy() {
		Level level = this.getLevel();
		BlockPos blockPos = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() + 1, this.getBlockPos().getZ());

		return level.isDay() && level.canSeeSky(blockPos);
	}

	@Override
	protected void generateEnergy() {
		this.generateEnergy(this.getGenerationInTick());
	}

	@Override
	protected List<Direction> getEjectDirections() {
		List<Direction> list = super.getEjectDirections();
		list.addAll(Arrays.stream(Direction.values()).filter(d -> d != Direction.UP).toList());
		return list;
	}

	@Override
	protected IEnergyStorage createGeneratingEnergyStorage() {
		int capacity = Config.SOLAR_PANEL_ENERGY_CAPACITY.get();
		int maxTransfer = Config.SOLAR_PANEL_ENERGY_TRANSFER.get();
		return new EnergyStorageBasic(this, capacity, maxTransfer, capacity);
	}

}
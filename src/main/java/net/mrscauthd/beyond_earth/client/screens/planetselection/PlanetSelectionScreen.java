package net.mrscauthd.beyond_earth.client.screens.planetselection;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.events.forge.PlanetSelectionScreenBackgroundRenderEvent;
import net.mrscauthd.beyond_earth.client.events.forge.PlanetSelectionScreenButtonVisibilityEvent;
import net.mrscauthd.beyond_earth.client.events.forge.PlanetSelectionScreenInitEvent;
import net.mrscauthd.beyond_earth.client.events.forge.PlanetSelectionScreenRenderEvent;
import net.mrscauthd.beyond_earth.client.screens.buttons.ModifiedButton;
import net.mrscauthd.beyond_earth.client.screens.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.client.screens.planetselection.helper.CategoryHelper;
import net.mrscauthd.beyond_earth.client.screens.planetselection.helper.PlanetSelectionScreenHelper;
import net.mrscauthd.beyond_earth.common.menus.planetselection.PlanetSelectionMenu;
import net.mrscauthd.beyond_earth.common.registries.NetworkRegistry;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class PlanetSelectionScreen extends Screen implements MenuAccess<PlanetSelectionMenu.GuiContainer> {

	/** TEXTURES */
	public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/gui/planet_selection.png");

	public static final ResourceLocation SCROLLER_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/gui/util/scroller.png");

	public static final ResourceLocation SMALL_BUTTON_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/gui/util/buttons/small_button.png");
	public static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/gui/util/buttons/button.png");
	public static final ResourceLocation LARGE_BUTTON_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/gui/util/buttons/large_button.png");

	public static final ResourceLocation MILKY_WAY_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/gui/util/milky_way.png");

	public static final ResourceLocation SUN_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/sun.png");
	public static final ResourceLocation MARS_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/mars.png");
	public static final ResourceLocation EARTH_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/earth.png");
	public static final ResourceLocation VENUS_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/venus.png");
	public static final ResourceLocation MERCURY_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/mercury.png");
	public static final ResourceLocation GLACIO_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/glacio.png");

	public static final ResourceLocation SMALL_MENU_LIST = new ResourceLocation(BeyondEarth.MODID, "textures/gui/util/planet_menu.png");
	public static final ResourceLocation LARGE_MENU_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/gui/util/large_planet_menu.png");

	/** TEXT */
	public static final Component CATALOG_TEXT = PlanetSelectionScreenHelper.tl("catalog");
	public static final Component BACK_TEXT = PlanetSelectionScreenHelper.tl("back");

	public static final Component SUN_TEXT = PlanetSelectionScreenHelper.tl("sun");
	public static final Component PROXIMA_CENTAURI_TEXT = PlanetSelectionScreenHelper.tl("proxima_centauri");

	public static final Component SOLAR_SYSTEM_TEXT = PlanetSelectionScreenHelper.tl("solar_system");

	public static final Component SOLAR_SYSTEM_SUN_TEXT = PlanetSelectionScreenHelper.tl("solar_system_sun");
	public static final Component SOLAR_SYSTEM_PROXIMA_CENTAURI_TEXT = PlanetSelectionScreenHelper.tl("solar_system_proxima_centauri");

	public static final Component EARTH_TEXT = PlanetSelectionScreenHelper.tl("earth");
	public static final Component MARS_TEXT = PlanetSelectionScreenHelper.tl("mars");
	public static final Component MERCURY_TEXT = PlanetSelectionScreenHelper.tl("mercury");
	public static final Component VENUS_TEXT = PlanetSelectionScreenHelper.tl("venus");
	public static final Component GLACIO_TEXT = PlanetSelectionScreenHelper.tl("glacio");

	public static final Component PLANET_TEXT = PlanetSelectionScreenHelper.tl("planet");
	public static final Component MOON_TEXT = PlanetSelectionScreenHelper.tl("moon");

	public static final Component ORBIT_TEXT = PlanetSelectionScreenHelper.tl("orbit");

	public static final Component NO_GRAVITY_TEXT = PlanetSelectionScreenHelper.tl("no_gravity");

	public static final Component SPACE_STATION_TEXT = PlanetSelectionScreenHelper.tl("space_station");

	public static final Component CATEGORY_TEXT = PlanetSelectionScreenHelper.tl("category");
	public static final Component PROVIDED_TEXT = PlanetSelectionScreenHelper.tl("provided");
	public static final Component TYPE_TEXT = PlanetSelectionScreenHelper.tl("type");
	public static final Component GRAVITY_TEXT = PlanetSelectionScreenHelper.tl("gravity");
	public static final Component OXYGEN_TEXT = PlanetSelectionScreenHelper.tl("oxygen");
	public static final Component TEMPERATURE_TEXT = PlanetSelectionScreenHelper.tl("temperature");
	public static final Component OXYGEN_TRUE_TEXT = PlanetSelectionScreenHelper.tl("oxygen.true");
	public static final Component OXYGEN_FALSE_TEXT = PlanetSelectionScreenHelper.tl("oxygen.false");
	public static final Component ITEM_REQUIREMENT_TEXT = PlanetSelectionScreenHelper.tl("item_requirement");

	public static final Component ROCKET_TIER_1_TEXT = Component.translatable("entity." + BeyondEarth.MODID + ".rocket_t" + 1);
	public static final Component ROCKET_TIER_2_TEXT = Component.translatable("entity." + BeyondEarth.MODID + ".rocket_t" + 2);
	public static final Component ROCKET_TIER_3_TEXT = Component.translatable("entity." + BeyondEarth.MODID + ".rocket_t" + 3);
	public static final Component ROCKET_TIER_4_TEXT = Component.translatable("entity." + BeyondEarth.MODID + ".rocket_t" + 4);

	/** MENU */
	private PlanetSelectionMenu.GuiContainer menu;

	/** CATEGORY */
	public CategoryHelper category; //IF YOU DO A ADDON MOD SET THIS CATEGORY TO -1 AND CREATE A OWN WITH "AbstractCategoryHelper"

	/** BUTTON LISTS */
	public List<ModifiedButton> visibleButtons;

	/** ROTATIONS */
	public float rotationMilkyWay;
	public float rotationMars;
	public float rotationEarth;
	public float rotationVenus;
	public float rotationMercury;
	public float rotationGlacio;

	/** SOLAR SYSTEM BUTTONS */
	public ModifiedButton solarSystemButton;
	public ModifiedButton proximaCentauriButton;

	/** SUN CATEGORY BUTTONS */
	public ModifiedButton earthCategoryButton;
	public ModifiedButton marsCategoryButton;
	public ModifiedButton mercuryCategoryButton;
	public ModifiedButton venusCategoryButton;

	/** PROXIMA CENTAURI CATEGORY BUTTONS */
	public ModifiedButton glacioCategoryButton;

	/** SUN CATEGORY TELEPORT BUTTONS */
	public ModifiedButton earthButton;
	public ModifiedButton moonButton;
	public ModifiedButton marsButton;
	public ModifiedButton mercuryButton;
	public ModifiedButton venusButton;

	/** PROXIMA CENTAURI CATEGORY TELEPORT BUTTONS */
	public ModifiedButton glacioButton;

	/** BACK BUTTONS */
	public ModifiedButton backButton;

	/** SUN CATEGORY ORBIT TELEPORT BUTTONS */
	public ModifiedButton earthOrbitButton;
	public ModifiedButton moonOrbitButton;
	public ModifiedButton marsOrbitButton;
	public ModifiedButton mercuryOrbitButton;
	public ModifiedButton venusOrbitButton;

	/** PROXIMA CENTAURI CATEGORY ORBIT TELEPORT BUTTONS */
	public ModifiedButton glacioOrbitButton;

	/** SUN CATEGORY SPACE-STATION TELEPORT BUTTONS */
	public ModifiedButton earthSpaceStationButton;
	public ModifiedButton moonSpaceStationButton;
	public ModifiedButton marsSpaceStationButton;
	public ModifiedButton mercurySpaceStationButton;
	public ModifiedButton venusSpaceStationButton;

	/** PROXIMA CENTAURI CATEGORY SPACE-STATION TELEPORT BUTTONS */
	public ModifiedButton glacioSpaceStationButton;

	/** SPACE STATION RECIPE SYSTEM */
	//public SpaceStationRecipe recipe;
	public boolean spaceStationItemList;

	/** SCROLL SYSTEM */
	public int scrollIndex;

	/** BUTTON ROW END */
	public int rowEnd;

	public PlanetSelectionScreen(PlanetSelectionMenu.GuiContainer menu, Inventory inventory, Component p_96550_) {
		super(p_96550_);
		this.menu = menu;
	}

	@Override
	public PlanetSelectionMenu.GuiContainer getMenu() {
		return menu;
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBg(poseStack, partialTicks, mouseX, mouseY);
		super.render(poseStack, mouseX, mouseY, partialTicks);

		/** RENDER PRE EVENT FOR ADDONS */
		if (MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenRenderEvent.Pre(this, poseStack, partialTicks, mouseX, mouseY))) {
			return;
		}

		/** CATALOG TEXT RENDERER */
		this.font.draw(poseStack, CATALOG_TEXT, 24, (this.height / 2) - 143 / 2, -1);

		/** RENDER POST EVENT FOR ADDONS */
		MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenRenderEvent.Post(this, poseStack, partialTicks, mouseX, mouseY));
	}

	protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {

		/** RENDER BACKGROUND PRE EVENT FOR ADDONS */
		if (MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenBackgroundRenderEvent.Pre(this, poseStack, partialTicks, mouseX, mouseY))) {
			return;
		}

		/** BACKGROUND RENDERER */
		ScreenHelper.drawTexture(poseStack, 0, 0, this.width, this.height, BACKGROUND_TEXTURE, false);

		/** SUN RENDERER */
		if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 1, 7)) {
			ScreenHelper.drawTexture(poseStack, (this.width - 15) / 2, (this.height - 15) / 2, 15, 15, SUN_TEXTURE, false);
		}

		/** OBJECT ROTATIONS */
		this.rotationCalculator(partialTicks);

		/** RINGS */
		this.drawRings();

		/** ROTATED OBJECTS RENDERER */
		this.drawPlanets(poseStack);

		/** SMALL MENU RENDERER */
		if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 0, 1) || PlanetSelectionScreenHelper.categoryRange(this.category.get(), 6, 6)) {
			ScreenHelper.drawTexture(poseStack, 0, (this.height / 2) - 177 / 2, 105, 177, SMALL_MENU_LIST, true);
			this.drawScroller(poseStack, 92);
		}

		/** LARGE MENU RENDERER */
		if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 2, 5) || PlanetSelectionScreenHelper.categoryRange(this.category.get(), 7, 7)) {
			ScreenHelper.drawTexture(poseStack, 0, (this.height / 2) - 177 / 2, 215, 177, LARGE_MENU_TEXTURE, true);
			this.drawScroller(poseStack, 210);
		}

		/** RENDER BACKGROUND POST EVENT FOR ADDONS */
		MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenBackgroundRenderEvent.Post(this, poseStack, partialTicks, mouseX, mouseY));
	}

	@Override
	protected void init() {
		super.init();

		/** INIT PRE EVENT FOR ADDONS */
		if (MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenInitEvent.Pre(this))) {
			return;
		}

		/** ROW END */
		this.rowEnd = 5;

		/** SET CATEGORY */
		this.category = new CategoryHelper();

		/** SET PLANET ROTATIONS */
		this.rotationMilkyWay = 0;
		this.rotationMars = 0;
		this.rotationEarth = 90;
		this.rotationVenus = 180;
		this.rotationMercury = 270;
		this.rotationGlacio = 180;

		/** SET SCROLL */
		this.scrollIndex = 0;

		/** SPACE STATION RECIPE SYSTEM */
		//this.recipe = (SpaceStationRecipe) this.minecraft.level.getRecipeManager().byKey(SpaceStationRecipe.KEY).orElse(null);
		//this.spaceStationItemList = this.recipe.getIngredientStacks().stream().allMatch(this::getSpaceStationItemCheck);
		this.spaceStationItemList = true;

		/** SET BUTTON LISTS */
		this.visibleButtons = Lists.newArrayList();

		/** MAIN CATEGORY BUTTON 1 */
		solarSystemButton = PlanetSelectionScreenHelper.addCategoryButton(this, this.category, 10, 1, 70, 20, 1, true, false, ModifiedButton.ButtonTypes.MILKY_WAY_CATEGORY, List.of(SUN_TEXT.getString()), BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, SOLAR_SYSTEM_SUN_TEXT);

		/** BACK BUTTON */
		backButton = PlanetSelectionScreenHelper.addBackButton(this, 10, 1, 70, 20, false, BUTTON_TEXTURE, ModifiedButton.ColorTypes.BLUE, BACK_TEXT, (onPress) -> {
			if (this.category.get() == 1) {
				this.category.set(0);
				this.scrollIndex = 0;
				this.updateButtonVisibility();
			}
			else if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 2, 5)) {
				this.category.set(1);
				this.scrollIndex = 0;
				this.updateButtonVisibility();
			}
			else if (this.category.get() == 6) {
				this.category.set(0);
				this.scrollIndex = 0;
				this.updateButtonVisibility();
			}
			else if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 7, 7)) {
				this.category.set(6);
				this.scrollIndex = 0;
				this.updateButtonVisibility();
			}
		});

		/** CATEGORY 1 */
		earthCategoryButton = PlanetSelectionScreenHelper.addCategoryButton(this, this.category, 10, 1, 70, 20, 2, this.checkTier(1), false, ModifiedButton.ButtonTypes.SOLAR_SYSTEM_CATEGORY, List.of(EARTH_TEXT.getString(), ROCKET_TIER_1_TEXT.getString()), BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, EARTH_TEXT);

		marsCategoryButton = PlanetSelectionScreenHelper.addCategoryButton(this, this.category, 10, 1, 70, 20, 3, this.checkTier(2), false, ModifiedButton.ButtonTypes.SOLAR_SYSTEM_CATEGORY, List.of(MARS_TEXT.getString(), ROCKET_TIER_2_TEXT.getString()), BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, MARS_TEXT);

		mercuryCategoryButton = PlanetSelectionScreenHelper.addCategoryButton(this, this.category, 10, 1, 70, 20, 4, this.checkTier(3), false, ModifiedButton.ButtonTypes.SOLAR_SYSTEM_CATEGORY, List.of(MERCURY_TEXT.getString(), ROCKET_TIER_3_TEXT.getString()), BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, MERCURY_TEXT);

		venusCategoryButton = PlanetSelectionScreenHelper.addCategoryButton(this, this.category, 10, 1, 70, 20, 5, this.checkTier(3), false, ModifiedButton.ButtonTypes.SOLAR_SYSTEM_CATEGORY, List.of(VENUS_TEXT.getString(), ROCKET_TIER_3_TEXT.getString()), BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, VENUS_TEXT);

		/** TELEPORT BUTTONS */
		earthButton = PlanetSelectionScreenHelper.addHandlerButton(this, 10, 1, 70, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(0), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(PLANET_TEXT.getString(), "9.807 m/s", "a" + OXYGEN_TRUE_TEXT.getString(), "a" + "14"), BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, EARTH_TEXT);

		moonButton = PlanetSelectionScreenHelper.addHandlerButton(this, 10, 1, 70, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(1), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(MOON_TEXT.getString(), "1.62 m/s", "c" + OXYGEN_FALSE_TEXT.getString(), "a" + "-160"), BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, MOON_TEXT);

		marsButton = PlanetSelectionScreenHelper.addHandlerButton(this, 10, 1, 70, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(2), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(PLANET_TEXT.getString(), "3.721 m/s", "c" + OXYGEN_FALSE_TEXT.getString(), "a" + "-63"), BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, MARS_TEXT);

		mercuryButton = PlanetSelectionScreenHelper.addHandlerButton(this, 10, 1, 70, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(3), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(PLANET_TEXT.getString(), "3.7 m/s", "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "430"), BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, MERCURY_TEXT);

		venusButton = PlanetSelectionScreenHelper.addHandlerButton(this, 10, 1, 70, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(4), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(PLANET_TEXT.getString(), "8.87 m/s", "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "482"), BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, VENUS_TEXT);

		/** ORBIT TELEPORT BUTTONS */
		earthOrbitButton = PlanetSelectionScreenHelper.addHandlerButton(this, 84, 2, 37, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(5), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), SMALL_BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, ORBIT_TEXT);

		moonOrbitButton = PlanetSelectionScreenHelper.addHandlerButton(this, 84, 2, 37, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(6), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), SMALL_BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, ORBIT_TEXT);

		marsOrbitButton = PlanetSelectionScreenHelper.addHandlerButton(this, 84, 2, 37, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(7), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), SMALL_BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, ORBIT_TEXT);

		mercuryOrbitButton = PlanetSelectionScreenHelper.addHandlerButton(this, 84, 2, 37, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(8), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), SMALL_BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, ORBIT_TEXT);

		venusOrbitButton = PlanetSelectionScreenHelper.addHandlerButton(this, 84, 2, 37, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(9), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), SMALL_BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, ORBIT_TEXT);

		/** SPACE STATION TELEPORT BUTTONS */
		earthSpaceStationButton = PlanetSelectionScreenHelper.addHandlerButton(this, 125, 3, 75, 20, this.spaceStationItemList, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(10), ModifiedButton.ButtonTypes.PLANET_SPACE_STATION_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), LARGE_BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, SPACE_STATION_TEXT);

		moonSpaceStationButton = PlanetSelectionScreenHelper.addHandlerButton(this, 125, 3, 75, 20, this.spaceStationItemList, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(11), ModifiedButton.ButtonTypes.PLANET_SPACE_STATION_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), LARGE_BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, SPACE_STATION_TEXT);

		marsSpaceStationButton = PlanetSelectionScreenHelper.addHandlerButton(this, 125, 3, 75, 20, this.spaceStationItemList, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(12), ModifiedButton.ButtonTypes.PLANET_SPACE_STATION_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), LARGE_BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, SPACE_STATION_TEXT);

		mercurySpaceStationButton = PlanetSelectionScreenHelper.addHandlerButton(this, 125, 3, 75, 20, this.spaceStationItemList, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(13), ModifiedButton.ButtonTypes.PLANET_SPACE_STATION_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), LARGE_BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, SPACE_STATION_TEXT);

		venusSpaceStationButton = PlanetSelectionScreenHelper.addHandlerButton(this, 125, 3, 75, 20, this.spaceStationItemList, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(14), ModifiedButton.ButtonTypes.PLANET_SPACE_STATION_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), LARGE_BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, SPACE_STATION_TEXT);

		/** MAIN CATEGORY BUTTON 2 */
		proximaCentauriButton = PlanetSelectionScreenHelper.addCategoryButton(this, this.category, 10, 1, 70, 20, 6, true, false, ModifiedButton.ButtonTypes.MILKY_WAY_CATEGORY, List.of(PROXIMA_CENTAURI_TEXT.getString()), BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, SOLAR_SYSTEM_PROXIMA_CENTAURI_TEXT);

		/** PROXIMA CENTAURI SOLAR SYSTEM CATEGORY */
		glacioCategoryButton = PlanetSelectionScreenHelper.addCategoryButton(this, this.category, 10, 1, 70, 20, 7, this.checkTier(4), false, ModifiedButton.ButtonTypes.SOLAR_SYSTEM_CATEGORY, List.of(GLACIO_TEXT.getString(), ROCKET_TIER_4_TEXT.getString()), BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, GLACIO_TEXT);

		/** PROXIMA CENTAURI TELEPORT BUTTONS */
		glacioButton = PlanetSelectionScreenHelper.addHandlerButton(this, 10, 1, 70, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(15), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(PLANET_TEXT.getString(), "3.721 m/s", "a" + OXYGEN_TRUE_TEXT.getString(), "a" + "-20"), BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, GLACIO_TEXT);

		/** PROXIMA CENTAURI ORBIT TELEPORT BUTTONS */
		glacioOrbitButton = PlanetSelectionScreenHelper.addHandlerButton(this, 84, 2, 37, 20, true, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(16), ModifiedButton.ButtonTypes.PLANET_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), SMALL_BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE, ORBIT_TEXT);

		/** PROXIMA CENTAURI SPACE STATION TELEPORT BUTTONS */
		glacioSpaceStationButton = PlanetSelectionScreenHelper.addHandlerButton(this, 125, 3, 75, 20, this.spaceStationItemList, false, true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(17), ModifiedButton.ButtonTypes.PLANET_SPACE_STATION_CATEGORY, List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), LARGE_BUTTON_TEXTURE, ModifiedButton.ColorTypes.GREEN, SPACE_STATION_TEXT);

		/** INIT POST EVENT FOR ADDONS */
		MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenInitEvent.Post(this));

		/** UPDATE BUTTON VISIBILITY */
		this.updateButtonVisibility();
	}

	@Override
	public void onClose() {
		this.menu.getPlayer().closeContainer();
		super.onClose();
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return false;
	}

	@Override
	public boolean mouseScrolled(double p_99314_, double p_99315_, double p_99316_) {
		if (this.getVisibleButtons(1).size() > this.rowEnd) {
			if (p_99316_ == 1) {
				if (this.scrollIndex != 0) {
					this.scrollIndex = this.scrollIndex + 1;
					this.updateButtonVisibility();
					return true;
				}
			} else {
				if (this.scrollIndex != -(this.getVisibleButtons(1).size() - this.rowEnd)) {
					this.scrollIndex = this.scrollIndex - 1;
					this.updateButtonVisibility();
					return true;
				}
			}
		}

		return false;
	}

	public void updateButtonVisibility() {

		/** BUTTON VISIBILITY PRE EVENT FOR ADDONS */
		if (MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenButtonVisibilityEvent.Pre(this))) {
			return;
		}

		this.visibleButtons.clear();

		/** SOLAR SYSTEM VISIBLE LOGIC */
		this.visibleButton(this.solarSystemButton, this.category.get() == 0);
		this.visibleButton(this.proximaCentauriButton, this.category.get() == 0);

		/** BACK BUTTON VISIBLE LOGIC */
		this.visibleButton(this.backButton, PlanetSelectionScreenHelper.categoryRange(this.category.get(), 1, 5) || PlanetSelectionScreenHelper.categoryRange(this.category.get(), 6, 7));

		/** SUN CATEGORY VISIBLE LOGIC */
		this.visibleButton(this.earthCategoryButton, this.category.get() == 1);
		this.visibleButton(this.marsCategoryButton, this.category.get() == 1);
		this.visibleButton(this.mercuryCategoryButton, this.category.get() == 1);
		this.visibleButton(this.venusCategoryButton, this.category.get() == 1);

		/** SUN PLANET CATEGORY VISIBLE LOGIC */
		this.visibleButton(this.earthButton, this.category.get() == 2);
		this.visibleButton(this.moonButton, this.category.get() == 2);
		this.visibleButton(this.marsButton, this.category.get() == 3);
		this.visibleButton(this.mercuryButton, this.category.get() == 4);
		this.visibleButton(this.venusButton, this.category.get() == 5);

		/** SUN ORBIT CATEGORY VISIBLE LOGIC */
		this.visibleButton(this.earthOrbitButton, this.category.get() == 2);
		this.visibleButton(this.moonOrbitButton, this.category.get() == 2);
		this.visibleButton(this.marsOrbitButton, this.category.get() == 3);
		this.visibleButton(this.mercuryOrbitButton, this.category.get() == 4);
		this.visibleButton(this.venusOrbitButton, this.category.get() == 5);

		/** SUN SPACE STATION CATEGORY VISIBLE LOGIC */
		this.visibleButton(this.earthSpaceStationButton, this.category.get() == 2);
		this.visibleButton(this.moonSpaceStationButton, this.category.get() == 2);
		this.visibleButton(this.marsSpaceStationButton, this.category.get() == 3);
		this.visibleButton(this.mercurySpaceStationButton, this.category.get() == 4);
		this.visibleButton(this.venusSpaceStationButton, this.category.get() == 5);

		/** PROXIMA CENTAURI CATEGORY VISIBLE LOGIC */
		this.visibleButton(this.glacioCategoryButton, this.category.get() == 6);

		/** PROXIMA CENTAURI CATEGORY VISIBLE LOGIC */
		this.visibleButton(this.glacioButton, this.category.get() == 7);

		/** PROXIMA CENTAURI ORBIT CATEGORY VISIBLE LOGIC */
		this.visibleButton(this.glacioOrbitButton, this.category.get() == 7);

		/** PROXIMA CENTAURI SPACE STATION CATEGORY VISIBLE LOGIC */
		this.visibleButton(this.glacioSpaceStationButton, this.category.get() == 7);

		/** BUTTON VISIBILITY POST EVENT FOR ADDONS */
		MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenButtonVisibilityEvent.Post(this));
	}

	public void rotationCalculator(float partialTicks) {

		/** SOLAR SYSTEM CATEGORY */
		this.rotationMilkyWay = (this.rotationMilkyWay + partialTicks * 0.4f);

		/** SUN CATEGORY */
		this.rotationMars = (this.rotationMars + partialTicks * 0.01f);
		this.rotationEarth = (this.rotationEarth + partialTicks * 0.02f);
		this.rotationVenus = (this.rotationVenus + partialTicks * 0.02f);
		this.rotationMercury = (this.rotationMercury + partialTicks * 0.02f);

		/** PROXIMA CENTAURI CATEGORY */
		this.rotationGlacio = (this.rotationGlacio + partialTicks * 0.02f);
	}

	public void drawRings() {

		/** SUN CATEGORY */
		if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 1, 5)) {
			PlanetSelectionScreenHelper.drawCircle(this.width / 2, this.height / 2, 30, 180, new Vec3(179, 49, 44));
			PlanetSelectionScreenHelper.drawCircle(this.width / 2, this.height / 2, 50, 180, new Vec3(235, 136, 68));
			PlanetSelectionScreenHelper.drawCircle(this.width / 2, this.height / 2, 90, 180, new Vec3(53, 163, 79));
			PlanetSelectionScreenHelper.drawCircle(this.width / 2, this.height / 2, 130, 180, new Vec3(37, 49, 146));
		}

		/** PROXIMA CENTAURI CATEGORY */
		if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 6, 7)) {
			PlanetSelectionScreenHelper.drawCircle(this.width / 2, this.height / 2, 30, 180, new Vec3(37, 49, 146));
		}
	}

	public void drawPlanets(PoseStack poseStack) {

		/** SOLAR SYSTEM CATEGORY */
		if (this.category.get() == 0) {
			PlanetSelectionScreenHelper.drawGalaxy(this, poseStack, MILKY_WAY_TEXTURE, -125, -125, 250, 250, this.rotationMilkyWay);
		}

		/** SUN CATEGORY */
		if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 1, 5)) {
			PlanetSelectionScreenHelper.drawPlanet(this, poseStack, MERCURY_TEXT, MERCURY_TEXTURE, 30, 10, 10, this.rotationMercury);
			PlanetSelectionScreenHelper.drawPlanet(this, poseStack, VENUS_TEXT, VENUS_TEXTURE, 50, 10, 10, this.rotationVenus);
			PlanetSelectionScreenHelper.drawPlanet(this, poseStack, EARTH_TEXT, EARTH_TEXTURE, 90, 10, 10, this.rotationEarth);
			PlanetSelectionScreenHelper.drawPlanet(this, poseStack, MARS_TEXT, MARS_TEXTURE, 130, 10, 10, this.rotationMars);
		}

		/** PROXIMA CENTAURI CATEGORY */
		if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 6, 7)) {
			PlanetSelectionScreenHelper.drawPlanet(this, poseStack, GLACIO_TEXT, GLACIO_TEXTURE, 30, 10, 10, this.rotationGlacio);
		}
	}

	public void drawScroller(PoseStack poseStack, int x) {
		if (this.getVisibleButtons(1).size() > this.rowEnd) {

			int buttonStartY = (this.height / 2) - 67 / 2;
			int scrollSize = this.getVisibleButtons(1).size() - this.rowEnd;

			float y = buttonStartY + ((97.0F / scrollSize) * -this.scrollIndex);

			ScreenHelper.drawTexture(poseStack, x, (int) y, 4, 8, SCROLLER_TEXTURE, false);
		}
	}

	public void handleButtonPos(int rowStart, int rowEnd) {
		/** SET POS OF VISIBLE BUTTONS */
		for (int f1 = rowStart; f1 <= rowEnd; f1++) {
			for (int f2 = 0; f2 < this.getVisibleButtons(f1).size(); f2++) {
				ModifiedButton button = this.getVisibleButtons(f1).get(f2);

				int buttonStartY = (this.height / 2) - 68 / 2;

				int extraPos = 0;

				if (f1 >= 2 && rowEnd <= 3) {
					extraPos = 1;
				}

				int y = buttonStartY + (22 * (f2 + extraPos + this.scrollIndex));

				if (button.y != y) {
					button.setPosition(button.x, y);
				}
			}
		}
	}

	public boolean buttonScrollVisibility(ModifiedButton button) {
		int buttonStartY = (this.height / 2) - 68 / 2;
		int buttonEndY = buttonStartY + 22 * this.rowEnd;

		/** IF BUTTON ABOVE THE MENU */
		if (button.y < buttonStartY && button.row != 0) {
			return false;
		}

		/** IF BUTTON UNDER THE MENU */
		if (button.y >= buttonEndY && button.row != 0) {
			return false;
		}

		return true;
	}

	public List<ModifiedButton> getVisibleButtons(int row) {
		/** VISIBLE BUTTON LIST */
		List<ModifiedButton> listVisible = new ArrayList<>();

		/** ADD VISIBLE BUTTONS TO THE LIST */
		for (int f1 = 0; f1 < this.visibleButtons.size(); f1++) {
			ModifiedButton button = this.visibleButtons.get(f1);

			if (button.row == row) {
				listVisible.add(button);
			}
		}

		return listVisible;
	}

	public void visibleButton(ModifiedButton button, Boolean condition) {
		/** HANDLE VISIBLE BUTTON LIST */
		if (condition && !visibleButtons.contains(button)) {
			visibleButtons.add(button);
		}

		/** POS HANDLER */
		this.handleButtonPos(1, 3);

		/** BUTTON VISIBILITY */
		button.visible = condition && this.buttonScrollVisibility(button);
	}

	public boolean getSpaceStationItemCheck(/*IngredientStack ingredientStack*/) {
		/*Player player = this.menu.player;

		if (player.getAbilities().instabuild || player.isSpectator()) {
			return true;
		}

		Inventory inv = player.getInventory();
		int itemStackCount = 0;

		for (int i = 0; i < inv.getContainerSize(); ++i) {
			ItemStack itemStack = inv.getItem(i);

			if (ingredientStack.testWithoutCount(itemStack)) {
				itemStackCount += itemStack.getCount();
			}
		}*/

		return true; //itemStackCount >= ingredientStack.getCount();
	}

	public boolean checkTier(int tier) {
		return this.menu.getTier() >= tier;
	}

	/** USE THIS METHOD TO ADD A OWN BUTTON SYSTEM (YOU SHOULD USE THE PlanetGuiHelper ONE, IF YOU NEED NOT A OWN SYSTEM) */
	public ModifiedButton addButton(int x, int y, int row, int width, int height, boolean rocketCondition, ModifiedButton.ButtonTypes type, List<String> list, ResourceLocation buttonTexture, ModifiedButton.ColorTypes colorType, Component title, Button.OnPress onPress) {
		ModifiedButton button = this.addRenderableWidget(new ModifiedButton(x, y, row, width, height, 0, 0, 0, rocketCondition, type, list, buttonTexture, colorType, width, height, onPress, title));
		return button;
	}
}

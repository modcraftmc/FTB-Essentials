package com.feed_the_beast.mods.ftbessentials;

import com.feed_the_beast.mods.ftbessentials.net.FTBEssentialsNet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author LatvianModder
 */
@Mod(FTBEssentials.MOD_ID)
@Mod.EventBusSubscriber(modid = FTBEssentials.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FTBEssentials
{
	public static final String MOD_ID = "ftbessentials";
	public static final Logger LOGGER = LogManager.getLogger("FTB Essentials");
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().setLenient().create();

	public static FTBEssentials instance;
	public static FTBEssentialsCommon proxy;

	public FTBEssentials()
	{
		instance = this;
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
		FTBEssentialsNet.init();
		FTBEConfig.init();
		proxy = DistExecutor.safeRunForDist(() -> FTBEssentialsClient::new, () -> FTBEssentialsCommon::new);
	}
}
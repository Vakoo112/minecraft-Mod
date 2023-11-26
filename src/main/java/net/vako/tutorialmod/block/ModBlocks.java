package net.vako.tutorialmod.block;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vako.tutorialmod.Tutorialmod;
import net.vako.tutorialmod.block.custom.BlueberryCropBlock;
import net.vako.tutorialmod.block.custom.JumpyBlock;
import net.vako.tutorialmod.block.custom.ZirconLampBlock;
import net.vako.tutorialmod.item.ModCreativeModeTab;
import net.vako.tutorialmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorialmod.MOD_ID);


    public static final RegistryObject<Block> ZIRCON_BLOCK =
            registerBlock("zircon_block",
                    () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                            .strength(6f).
                            requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);


    public static final RegistryObject<Block> ZIRCONE_ORE =
            registerBlock("zircone_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .strength(6f).
                            requiresCorrectToolForDrops(), UniformInt.of(3,7)), ModCreativeModeTab.TUTORIAL_TAB);


    public static final RegistryObject<Block> JUMPY_BLOCK =
            registerBlock("jumpy_block",
                    () -> new JumpyBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .strength(6f).
                            requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> ZIRCON_LAMP =
            registerBlock("zircon_lamp",
                    () -> new ZirconLampBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .strength(6f).
                            requiresCorrectToolForDrops()
                            .lightLevel(state -> state.getValue(ZirconLampBlock.LIT) ? 15 : 0)),
                    ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> BLUEBERRY_CROP =
            BLOCKS.register("blueberry_crop",
                    () -> new BlueberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    private static <T extends  Block>RegistryObject<T> registerBlock
            (String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn,tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus event){
        BLOCKS.register(event);
    }
}

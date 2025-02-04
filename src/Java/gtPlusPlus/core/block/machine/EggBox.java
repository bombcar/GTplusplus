package gtPlusPlus.core.block.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gtPlusPlus.GTplusplus;
import gtPlusPlus.api.interfaces.ITileTooltip;
import gtPlusPlus.core.creative.AddToCreativeTab;
import gtPlusPlus.core.handler.GuiHandler;
import gtPlusPlus.core.item.base.itemblock.ItemBlockBasicTile;
import gtPlusPlus.core.lib.CORE;
import gtPlusPlus.core.tileentities.general.TileEntityEggBox;
import gtPlusPlus.core.util.minecraft.InventoryUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EggBox extends BlockContainer implements ITileTooltip
{
	@SideOnly(Side.CLIENT)
	private IIcon textureTop;
	@SideOnly(Side.CLIENT)
	private IIcon textureBottom;
	@SideOnly(Side.CLIENT)
	private IIcon textureFront;

	/**
	 * Determines which tooltip is displayed within the itemblock.
	 */
	private final int mTooltipID = 7;
	public final int field_149956_a = 0;

	@Override
	public int getTooltipID() {
		return this.mTooltipID;
	}

	@SuppressWarnings("deprecation")
	public EggBox(){
		super(Material.wood);
		this.setBlockName("blockEggBox");
		this.setCreativeTab(AddToCreativeTab.tabMachines);
		this.setHardness(5f);
		this.setResistance(1f);
		GameRegistry.registerBlock(this, ItemBlockBasicTile.class, "blockEggBox");
		LanguageRegistry.addName(this, "Egg Box");

	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return true;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		return super.getRenderType();	
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int p_149691_1_, final int p_149691_2_)
	{
		return p_149691_1_ == 1 ? this.textureTop : this.textureFront;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(CORE.MODID + ":" + "TileEntities/" + "EggBox_top");
		this.textureTop = p_149651_1_.registerIcon(CORE.MODID + ":" + "TileEntities/" + "EggBox_top");
		this.textureBottom = p_149651_1_.registerIcon(CORE.MODID + ":" + "TileEntities/" + "EggBox_side");
		this.textureFront = p_149651_1_.registerIcon(CORE.MODID + ":" + "TileEntities/" + "EggBox_side");
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int side, final float lx, final float ly, final float lz)
	{
		if (world.isRemote) {
			return true;
		}

		final TileEntity te = world.getTileEntity(x, y, z);
		if ((te != null) && (te instanceof TileEntityEggBox)){
			player.openGui(GTplusplus.instance, GuiHandler.GUI17, world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public int getRenderBlockPass() {
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int p_149915_2_) {
		return new TileEntityEggBox();
	}

	@Override
	public void onBlockAdded(final World world, final int x, final int y, final int z) {
		super.onBlockAdded(world, x, y, z);
	}

	@Override
	public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int number) {
		InventoryUtils.dropInventoryItems(world, x, y, z, block);
		super.breakBlock(world, x, y, z, block, number);
	}

	@Override
	public void onBlockPlacedBy(final World world, final int x, final int y, final int z, final EntityLivingBase entity, final ItemStack stack) {
		if (stack.hasDisplayName()) {
			((TileEntityEggBox) world.getTileEntity(x,y,z)).setCustomName(stack.getDisplayName());
		}
	}

	@Override
	public boolean canCreatureSpawn(final EnumCreatureType type, final IBlockAccess world, final int x, final int y, final int z) {
		return false;
	}

	/*@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
		TileEntityFishTrap te = (TileEntityFishTrap) world.getTileEntity(pos);
	    InventoryHelper.dropInventoryItems(world, pos, te);
	    super.breakBlock(world, pos, blockstate);
	}


	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	    if (stack.hasDisplayName()) {
	        ((TileEntityFishTrap) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
	    }
	}*/

	/**
	 * Update Chest Meta - Stub
	 * @param aWorld
	 * @param xPos
	 * @param yPos
	 * @param zPos
	 */
	@Deprecated
	public void func_149954_e(World aWorld, int xPos, int yPos, int zPos)
	{

	}

}
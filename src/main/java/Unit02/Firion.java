package Unit02;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import com.materiabot.IO.JSON.UnitParser;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Firion")
public class Firion extends Unit{
	public Firion() { super("Firion"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		switch(type) {
			case S1:
				ret = Region.GL.equals(region) ? new BestAbilities(this, 10743, 10742, 10743) :
												 new BestAbilities(this, 15434, 15433, 15434); break;
			case S2:
				ret = Region.GL.equals(region) ? new BestAbilities(this, 10749, 10890, 10749) :
					 							 new BestAbilities(this, 15442, 15440, 15442); break;
			case EX:
				ret = Region.GL.equals(region) ? new BestAbilities(this, 6013, 6012, 6013) :
					 							 new BestAbilities(this, 15444, 15443, 15444); break;
			default:
				break;
		}
		if(ret == null)
			return super.getAbility(type, region);
		if(ret.getAbilities().isEmpty())
			ret.getAbilities().addAll(super.getAbility(type, region).getAbilities());
		return ret;
	}
	
	@Override
	public void loadFixGL() {
		this.getSpecificAbility(10742).getAilments().removeIf(a -> a.getId() == 111);
		this.getSpecificAbility(10743).getAilments().removeIf(a -> a.getId() == 111);
		this.getSpecificAbility(10749).getAilments().removeIf(a -> a.getId() == 105);
	}
	@Override
	public void loadFixJP() {
		loadFixGL();
		this.getSpecificAbility(15446).getAilments().removeIf(a -> a.getRank() == 1);
		UnitParser.fixBT(this, 15448, 500, 600, null);
	}
}
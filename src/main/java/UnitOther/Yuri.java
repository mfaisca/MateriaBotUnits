package UnitOther;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Yuri")
public class Yuri extends Unit{
	public Yuri() { super("Yuri"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		if(Region.JP.equals(region))
			switch(type) {
				case BRV:
					ret = new BestAbilities(this, 15732, 6810, 15732, 15733, 15734); break;
				case LD:
					ret = new BestAbilities(this, 15731, 15731, 15733); break;
				default:
					break;
			}
		if(ret == null)
			return super.getAbility(type, region);
		if(ret.getAbilities().isEmpty())
			ret.getAbilities().addAll(super.getAbility(type, region).getAbilities());
		return ret;
	}
}
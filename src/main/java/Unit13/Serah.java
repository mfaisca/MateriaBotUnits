package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Serah")
public class Serah extends Unit{
	public Serah() { super("Serah"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		switch(type) {
			case LD:
				ret = new BestAbilities(this, 13953);
				break;
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
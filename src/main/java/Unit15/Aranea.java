package Unit15;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Aranea")
public class Aranea extends Unit{
	public Aranea() {
		super("Aranea", "ara", "arabae", "ara ara");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9288};
				break;
			case HP:
				ids = new int[]{9289};
				break;
			case S1:
				ids = new int[]{8924, 8925};
				break;
			case S2:
				ids = new int[]{8932, 8933};
				break;
			case EX:
				ids = new int[]{8938, 8939};
				break;
			case AA:
				ids = new int[]{8949};
				break;
			case LD:
				ids = new int[]{11703, 11704};
				break;
			case BT:
				ids = new int[]{};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 11704: //LD+
				ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(1668).getName() + "**」 is active", 0);
				break;
			case 8925: //S1+
			case 8933: //S2+
			case 8939: //EX+
				ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(1668).getName() + "**」 is active", 0);
			case 8924: //S1
			case 8932: //S2
			case 8938: //EX
				ret.addStaticAilmentEffect(1668, "Granted on BREAK", 0);
				ret.fixStupidCriticalDamage(1673, 50);
			case 9288: //BRV
			case 9289: //HP
				if(ret.getName().contains("+"))
					ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(1668).getName() + "**」 is active", 0);
				break;
		}
		return ret;
	}
}
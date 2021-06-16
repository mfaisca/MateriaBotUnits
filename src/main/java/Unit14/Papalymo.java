package Unit14;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Papalymo")
public class Papalymo extends Unit{
	public Papalymo() {
		super("Papalymo", "papa", "popolymo", "totolymo", "papagod");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7325, 7327};
				break;
			case HP:
				ids = new int[]{7326, 7319};
				break;
			case S1:
				ids = new int[]{7076, 7325, 7326};
				break;
			case S2:
				ids = new int[]{7080, 7081, 7327, 7319};
				break;
			case EX:
				ids = new int[]{7085, 7086, 7087};
				break;
			case AA:
				ids = new int[]{7052};
				break;
			case LD:
				ids = new int[]{};
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
		Ailment ailPoly = this.getSpecificAilment(1326);
		ailPoly.setName("Polyglot");
		switch(ret.getId()) {
			case 7325:
			case 7326:
				ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(178).getName() + "**」 is active", 0);
				break;
			case 7327:
			case 7319:
				ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(181).getName() + "**」 is active", 0);
				break;
			case 7081: //S2+
				ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(178).getName() + "**」 is active", 0);
			case 7085: //EX+Fire
			case 7086: //EX+Ice
			case 7087: //Foul
				if(!ret.getDetails().getAilments().contains(ailPoly))
					ret.getDetails().getAilments().add(ailPoly);
		}
		return ret;
	}
}
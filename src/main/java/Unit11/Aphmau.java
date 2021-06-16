package Unit11;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Aphmau")
public class Aphmau extends Unit{
	public Aphmau() {
		super("Aphmau", "aph");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4993, 8449};
				break;
			case HP:
				ids = new int[]{8645, 8448};
				break;
			case S1:
				ids = new int[]{8463};
				break;
			case S2:
				ids = new int[]{8465};
				break;
			case EX:
				ids = new int[]{8615, 8449, 8448};
				break;
			case AA:
				ids = new int[]{4932};
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
		switch(ret.getId()) {
			case 8448: //EX+HP+
			case 8449: //EX+BRV+
				ret.addStaticHit("Enabled when ?**" + getSpecificAilment(1520).getName() + "**」 is active", 0);
			case 8463: //S1
			case 8465: //S2
				ret.fixMissingAuraAilment(1038, 239, Ailment.EffectType.E8, Ailment.Target.Party);
				break;
			case 8615: //EX
				ret.fixMissingAuraAilment(1574, 583, Ailment.EffectType.E4, Ailment.Target.Party);
				ret.addStaticAilmentEffect(1520, "Enables ?**" + getSpecificAbility(8448).getName() + "**」");
				ret.addStaticAilmentEffect(1520, "Enables ?**" + getSpecificAbility(8449).getName() + "**」");
				break;
		}
		return ret;
	}
}
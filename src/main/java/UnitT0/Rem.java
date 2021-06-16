package UnitT0;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Rem")
public class Rem extends Unit{
	public Rem() {
		super("Rem", "remski");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{753};
				break;
			case HP:
				ids = new int[]{2064};
				break;
			case S1:
				ids = new int[]{5540};
				break;
			case S2:
				ids = new int[]{5542};
				break;
			case EX:
				ids = new int[]{5538};
				break;
			case AA:
				ids = new int[]{5535};
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
			case 5540: //S1
				ret.getHitDataById(2261).setTarget(Ability.Details.Hit_Data.Target.Ally);
				ret.getHitDataById(2260).setTarget(Ability.Details.Hit_Data.Target.Ally);
				ret.getHitDataById(6258).setTarget(Ability.Details.Hit_Data.Target.Ally);
				ret.fixMissingAuraAilment(1149, 315, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1149, 316, null, Ailment.Target.Party);
				break;
			case 5538: //EX
				ret.getAilmentById(136).setFake("ATK Up (Invisible)", null, Ailment.Emotes.BUFF_INVISIBLE.get());
			case 5535: //AA
				ret.fixMissingAuraAilment(1139, 308, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}
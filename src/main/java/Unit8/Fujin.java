package Unit8;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Fujin")
public class Fujin extends Unit{
	public Fujin() {
		super("Fujin", "fu-jin", "wind god", "wind goddess", "fuujin");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL": ids = new int[]{8200}; break;
					case "JP": ids = new int[]{12622}; break;
				}
				break;
			case HP:
				ids = new int[]{8201};
				break;
			case S1:
				ids = new int[]{8190, 8191};
				break;
			case S2:
				ids = new int[]{8194, 8195};
				break;
			case EX:
				ids = new int[]{8199, 7700};
				break;
			case AA:
				ids = new int[]{5763};
				break;
			case LD:
				ids = new int[]{12559};
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
			case 8190: //S1
			case 8194:{ //S2
				ret.addStaticHit("Enables ?**Wind Mode**」 for 6 turns");
				ret.fixMissingAuraAilment(1536, 595, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1536, 596, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1536, 597, null, Ailment.Target.Party);
				}break;
			case 8191: //S1+
			case 8195: //S2+
				ret.addStaticHit("Enabled when ?**Wind Mode**」 is active");
				ret.fixMissingAuraAilment(1536, 595, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1536, 596, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1536, 597, null, Ailment.Target.Party);
				break;
			case 5763:
				ret.fixMissingAuraAilment(1161, 321, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
			case 8199:
				ret.addStaticHit("Enables ?**Wind Mode**」 for 6 turns");
				Ailment ailEX = ret.getAilmentById(1537);
				ailEX.getArgs()[ailEX.getRank()] = 7700;
				break;
			case 7700:
				ret.setType(null);
				ret.addStaticHit("Cannot initiate a chase sequence");
				break;
		}
		return ret;
	}
}
package Unit2;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.The_Emperor")
public class The_Emperor extends Unit{
	public The_Emperor() {
		super("The Emperor", "emperor", "mateus");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6286, 9788};
				break;
			case HP:
				ids = new int[]{6287, 9789};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{6026, 6028}; break;
					case "JP":
						ids = new int[]{11641, 6028}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{6040, 6042}; break;
					case "JP":
						ids = new int[]{11647, 6042}; break;
				}
				break;
			case EX:
				ids = new int[]{6056, 6042, 6028, 6058};
				break;
			case AA:
				ids = new int[]{6065};
				break;
			case LD:
				ids = new int[]{9735};
				break;
			case BT:
				ids = new int[]{11234, 11649};
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
		String name = null;
		switch(ret.getId()) {
			case 9788: //BRV+++
			case 9789: //HP+++
				ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(1803).getName() + "**」 is active", 0);
				ret.addStaticHit("Trigger existing ?**" + super.getSpecificAilment(415).getName() + "**」 on target", 1);
				ret.addStaticHit("Trigger existing ?**" + super.getSpecificAilment(413).getName() + "**」 on target", 2);
				ret.addStaticHit("Trigger existing ?**" + super.getSpecificAilment(417).getName() + "**」 on target", 3);
				break;
			case 9735: //LD
				ret.addStaticHit("Trigger ?**" + super.getSpecificAilment(415).getName() + "**」 on target", 0);
				ret.addStaticHit("Trigger ?**" + super.getSpecificAilment(413).getName() + "**」 on target", 1);
				ret.addStaticHit("Trigger ?**" + super.getSpecificAilment(417).getName() + "**」 on target", 2);
				break;
			case 11234:
				ret.fixMissingAuraAilment(2056, 1025, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2056, 1026, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2056, 936, null, Ailment.Target.Party);
				break;
			case 6028: //S1 Trap
				name = name != null ? name : super.getSpecificAilment(413).getName();
			case 6042: //S2 Trap
				name = name != null ? name : super.getSpecificAilment(415).getName();
			case 6058: //EX Trap
				name = name != null ? name : super.getSpecificAilment(417).getName();
			case 11649: //BT Trap
				name = name != null ? name : super.getSpecificAilment(2431).getName();
				ret.setName(name);
				ret.setUseCount(-1);
				ret.fixDelayHitData(6695);
				ret.fixMissingAuraAilment(1209, 362, Ailment.EffectType.E3, Ailment.Target.AoE);
				break;
			case 6026: //S1
				ret.getAilmentById(413).removeEffect(53, 85);
				ret.addStaticHit("Trigger existing ?**" + super.getSpecificAilment(413).getName() + "**」 on target", 0);
				ret.addStaticAilmentEffect(413, "Trigger ?**" + super.getSpecificAilment(413).getName() + "**」 on expiration");
				ret.fixMissingAuraAilment(1206, 360, Ailment.EffectType.E3, Ailment.Target.AoE);
				break;
			case 6040: //S2
				ret.getAilmentById(415).removeEffect(53, 85);
				ret.addStaticHit("Trigger existing ?**" + super.getSpecificAilment(415).getName() + "**」 on target", 0);
				ret.addStaticAilmentEffect(415, "Trigger ?**" + super.getSpecificAilment(415).getName() + "**」 on expiration");
				break;
			case 6056: //EX
				ret.addStaticHit("Trigger existing ?**" + super.getSpecificAilment(415).getName() + "**」 on target", 0);
				ret.addStaticHit("Trigger existing ?**" + super.getSpecificAilment(413).getName() + "**」 on target", 1);
				ret.addStaticHit("Trigger existing ?**" + super.getSpecificAilment(417).getName() + "**」 on target", 2);
				ret.getAilmentById(413).removeEffect(53, 85);
				ret.getAilmentById(415).removeEffect(53, 85);
				ret.getAilmentById(417).removeEffect(53, 85);
				ret.addStaticAilmentEffect(413, "Trigger ?**" + super.getSpecificAilment(413).getName() + "**」 on expiration");
				ret.addStaticAilmentEffect(415, "Trigger ?**" + super.getSpecificAilment(415).getName() + "**」 on expiration");
				ret.addStaticAilmentEffect(417, "Trigger ?**" + super.getSpecificAilment(417).getName() + "**」 on expiration");
				ret.fixMissingAuraAilment(1206, 360, Ailment.EffectType.E3, Ailment.Target.AoE);
				ret.fixMissingAuraAilment(1209, 362, Ailment.EffectType.E3, Ailment.Target.AoE);
				break;
			case 6065: //AA
				ret.getAilmentById(1245).setRank(2);
				break;
		}
		return ret;
	}
}
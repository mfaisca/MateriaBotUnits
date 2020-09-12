package Unit8;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Rinoa")
public class Rinoa extends Unit{
	public Rinoa() {
		super("Rinoa");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {//
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL":
						ids = new int[]{3643, 3649};
						break;
					case "JP":
						ids = new int[]{9516, 9499};
						break;
				}
				break;
			case HP:
				switch(region) {
					case "GL":
						ids = new int[]{3646, 3652};
						break;
					case "JP":
						ids = new int[]{9519, 9503};
						break;
				}
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{3631, 3643, 3646};
						break;
					case "JP":
						ids = new int[]{9528, 9516, 9519};
						break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{3637, 3649, 3652};
						break;
					case "JP":
						ids = new int[]{3637, 9499, 9503};
						break;
				}
				break;
			case EX:
				switch(region) {
					case "GL":
						ids = new int[]{6521, 3649, 3652};
						break;
					case "JP":
						ids = new int[]{6521, 9499, 9503};
						break;
				}
				break;
			case AA:
				ids = new int[]{6763};
				break;
			case LD:
				ids = new int[]{9565, 9516, 9519};
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
			case 9528: //JP S1
				Ailment.EffectGrouping eg = new Ailment.EffectGrouping();
				eg.effectId = Ailment.EffectType.E60.getId();
				ret.getAilmentById(820).getEffects().add(eg);
				Ailment.Aura a = new Ailment.Aura();
				a.rankData = new String[] {null, "40040"};
				a.target = Ailment.Target.Party.getId();
				a.ailmentEffect = Ailment.EffectType.E1.getId();
				ret.getAilmentById(820).getAuras().put(123, a);
				a = new Ailment.Aura();
				a.rankData = new String[] {null, "40040"};
				a.target = Ailment.Target.Party.getId();
				a.ailmentEffect = Ailment.EffectType.E5.getId();
				ret.getAilmentById(820).getAuras().put(124, a);
			case 3631: //GL S1
				ret.removeHitDataById(3999);
				break;
			case 3637: //S2
			case 6521: //EX
				if(ret.getAilmentById(804).getEffects().size() == 4) {
					ret.getAilmentById(804).setName("Angel Wing");
					ret.getAilmentById(804).getEffects().get(2).rankData = new String[] {"25050-80", "60080-80"};
					ret.getAilmentById(804).getEffects().remove(3);
				}
				break;
			case 9565: //GL S1
				ret.removeHitDataById(3999);
				ret.fixMissingAuraAilment(1776, 799, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1776, 800, Ailment.EffectType.E7, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}
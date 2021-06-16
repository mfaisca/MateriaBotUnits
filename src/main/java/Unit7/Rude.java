package Unit7;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Rude")
public class Rude extends Unit{
	public Rude() {
		super("Rude");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{10476};
				break;
			case HP:
				ids = new int[]{10477};
				break;
			case S1:
				ids = new int[]{9952};
				break;
			case S2:
				ids = new int[]{9956};
				break;
			case EX:
				ids = new int[]{9960};
				break;
			case AA:
				ids = new int[]{9966};
				break;
			case LD:
				ids = new int[]{9962};
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
			case 10476: //BRV++
			case 10477: //HP++
				ret.fixStupidCriticalDamage(1611, 20);
				break;
			case 9952: //S1
			case 9956:{ //S2
				ret.fixStupidCriticalDamage(1611, 50);
				Ailment a = ret.getAilmentById(1837);
				a.getAuras().get(819).ailmentEffect = Ailment.EffectType.E1.getId();
				a.getAuras().get(819).target = Ailment.Target.Party.getId();
				a.getAuras().get(820).ailmentEffect = Ailment.EffectType.E5.getId();
				a.getAuras().get(820).target = Ailment.Target.Party.getId();
				a.getAuras().get(821).ailmentEffect = Ailment.EffectType.E58.getId();
				a.getAuras().get(821).target = Ailment.Target.Party.getId();
				a.getAuras().get(821).rankData[0] = a.getAuras().get(821).rankData[0].replace("-", "");
			}break;
			case 9960:{ //EX
				ret.fixStupidCriticalDamage(1611, 50);
				Ailment a = ret.getAilmentById(1838);
				a.getAuras().get(823).ailmentEffect = Ailment.EffectType.E67.getId();
				a.getAuras().get(823).target = Ailment.Target.Party.getId();
				}break;
			case 9962:{ //LD
				ret.fixStupidCriticalDamage(1611, 50);
				Ailment a = ret.getAilmentById(1839);
				a.getAuras().get(903).target = Ailment.Target.Party.getId();
				}break;
			case 9966:{ //AA
				Ailment a = ret.getAilmentById(1988);
				a.getAuras().get(904).ailmentEffect = Ailment.EffectType.E5.getId();
				a.getAuras().get(904).target = Ailment.Target.Party.getId();
				a.getAuras().get(905).ailmentEffect = Ailment.EffectType.E67.getId();
				a.getAuras().get(905).target = Ailment.Target.Party.getId();
				a.getAuras().get(906).ailmentEffect = Ailment.EffectType.E58.getId();
				a.getAuras().get(906).target = Ailment.Target.Party.getId();
				a.getAuras().get(906).rankData[0] = a.getAuras().get(906).rankData[0].replace("-", "");
				}break;
		}
		return ret;
	}
}
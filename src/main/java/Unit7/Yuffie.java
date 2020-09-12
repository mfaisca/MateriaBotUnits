package Unit7;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Yuffie")
public class Yuffie extends Unit{
	public Yuffie() {
		super("Yuffie", "yuff", "yubbie");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6373};
				break;
			case HP:
				ids = new int[]{6374};
				break;
			case S1:
				ids = new int[]{6177};
				break;
			case S2:
				ids = new int[]{6179};
				break;
			case EX:
				ids = new int[]{6182};
				break;
			case AA:
				ids = new int[]{6147};
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
			case 6177: //S1
				ret.getHitDataById(6942).getEffect().setEffect(Ability.Details.Hit_Data.EffectType.E48_2);
				break;
			case 6179: //S2
				ret.getAilmentById(386).getEffects().get(2).rankData[8] = ret.getAilmentById(386).getEffects().get(2).rankData[2];
				ret.removeHitDataById(723);
				break;
			case 6182:{ //EX
				Ailment.Aura a = ret.getAilmentById(1229).getAuras().get(378);
				a.ailmentEffect = Ailment.EffectType.E1.getId();
				a.target = Ailment.Target.Party.getId();
				a = ret.getAilmentById(1229).getAuras().get(380);
				a.ailmentEffect = Ailment.EffectType.E5.getId();
				a.target = Ailment.Target.Party.getId();
			} break;
			case 6147:{ //AA
				ret.removeHitDataById(6852);
				Ailment.Aura a = ret.getAilmentById(1220).getAuras().get(374);
				a.ailmentEffect = Ailment.EffectType.E5.getId();
				a.target = Ailment.Target.Party.getId();
			} break;
		}
		return ret;
	}
}
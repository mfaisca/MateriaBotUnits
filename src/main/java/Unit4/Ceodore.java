package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ceodore")
public class Ceodore extends Unit{
	public Ceodore() {
		super("Ceodore", "Ceo");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{11586, 11584};
				break;
			case HP:
				ids = new int[]{11587, 11585};
				break;
			case S1:
				ids = new int[]{10671};
				break;
			case S2:
				ids = new int[]{10675};
				break;
			case EX:
				ids = new int[]{10679, 10697};
				break;
			case AA:
				ids = new int[]{10685};
				break;
			case LD:
				ids = new int[]{11278};
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
			case 11584:
			case 11585:
				ret.addStaticHit("Enabled when 「**" + getSpecificAilment(2041).getName() + "**」 is active", 0);
				break;
			case 11278: //LD
				ret.fixMissingAuraAilment(2042, 926, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2042, 927, Ailment.EffectType.E5, Ailment.Target.Party);
			case 10675: //S2
				ret.fixRemoveDispels();
				ret.getAilmentById(2041).removeEffect(194, 184);
				ret.addStaticAilmentEffect(2041, "Reduce own HP by 80% on expiration");
				break;
			case 10685: //AA
				ret.fixMissingAuraAilment(2036, 1005, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
				
		}
		return ret;
	}
}
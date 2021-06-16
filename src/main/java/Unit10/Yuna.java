package Unit10;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Yuna")
public class Yuna extends Unit{
	public Yuna() {
		super("Yuna", "tuna", "yunie", "yunnie", "yoona", "miss yoona", "mish yoona", "lady yuna");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5872};
				break;
			case HP:
				ids = new int[]{5873};
				break;
			case S1:
				ids = new int[]{1899};
				break;
			case S2:
				ids = new int[]{4350};
				break;
			case EX:
				ids = new int[]{5867};
				break;
			case AA:
				ids = new int[]{4355};
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
			case 5867:
				ret.fixMissingAuraAilment(1213, 366, Ailment.EffectType.E7, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1213, 367, Ailment.EffectType.E8, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}
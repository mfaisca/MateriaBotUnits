package Unit12;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ashe")
public class Ashe extends Unit{
	public Ashe() {
		super("Ashe", "ashelia", "amalia", "ashelia b'nargin dalmasca", "ashelia bnargin dalmasca");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7367};
				break;
			case HP:
				ids = new int[]{7366};
				break;
			case S1:
				ids = new int[]{4102};
				break;
			case S2:
				ids = new int[]{4104};
				break;
			case EX: 
				ids = new int[]{7363, 7364};
				break;
			case AA:
				ids = new int[]{6657};
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
			case 6657:
				ret.fixMissingAuraAilment(1285, 408, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1285, 430, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 7363:
				ret.addStaticHit("Trigger ?**" + super.getSpecificAbility(7364).getName() + "**」 after this ability", 0);
				break;
		}
		return ret;
	}
}
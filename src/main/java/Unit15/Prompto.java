package Unit15;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Prompto")
public class Prompto extends Unit{
	public Prompto() {
		super("Prompto", "shortcake");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8384, 7994};
				break;
			case HP:
				ids = new int[]{8386, 7996};
				break;
			case S1:
				ids = new int[]{7986};
				break;
			case S2:
				ids = new int[]{7990};
				break;
			case EX:
				ids = new int[]{7998, 8317, 8320};
				break;
			case AA:
				ids = new int[]{4397};
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
			case 7998:
				ret.addStaticHit("Enables ?**" + super.getSpecificAbility(8317).getName() + "**」 for 1 turn");
				ret.addStaticHit("Enables ?**" + super.getSpecificAbility(8320).getName() + "**」 for 1 turn");
				ret.fixMissingAuraAilment(1011, 208, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1011, 207, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
			case 8317:
			case 8320:
				ret.addStaticHit("Enabled after using ?**" + super.getSpecificAbility(7998).getName() + "**」 for 1 turn", 0);
				break;
		}
		return ret;
	}
}
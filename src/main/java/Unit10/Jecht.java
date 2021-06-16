package Unit10;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Jecht")
public class Jecht extends Unit{
	public Jecht() {
		super("Jecht", "sir jecht", "sin", "bfa");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{3769};
				break;
			case HP:
				ids = new int[]{6808};
				break;
			case S1:
				ids = new int[]{3761, 3762};
				break;
			case S2:
				ids = new int[]{3765, 3766};
				break;
			case EX:
				ids = new int[]{6605};
				break;
			case AA:
				ids = new int[]{6171};
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
	
	private boolean s2 = false;
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 3762:
				ret.addHits(Ability.Details.Hit_Data.Attack_Type.Melee, Ability.Details.Hit_Data.Type.HP, Ability.Details.Hit_Data.Target.ST);
				ret.addStaticHit("Enabled when BRV >= 30% Max BRV", 0);
				break;
			case 3766:
				if(!s2) {
					s2 = true;
					ret.getDetails().getHits().addAll(ret.getDetails().getHits());
				}
				ret.addStaticHit("Enabled when BRV >= 30% Max BRV", 0);
				break;
			case 6605:
				ret.getAilmentById(602).setName("Free Turn");
				ret.getAilmentById(602).setFake(null, null, Ailment.Emotes.BUFF_INVISIBLE.get());
				break;
		}
		return ret;
	}
}
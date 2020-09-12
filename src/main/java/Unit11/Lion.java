package Unit11;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Lion")
public class Lion extends Unit{
	public Lion() {
		super("Lion");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4926};
				break;
			case HP:
				ids = new int[]{8443};
				break;
			case S1:
				ids = new int[]{4920, 4921};
				break;
			case S2:
				ids = new int[]{4924, 4925};
				break;
			case EX:
				ids = new int[]{4927, 4928};
				break;
			case AA:
				ids = new int[]{4966};
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
			case 4920:
				ret.addStaticHit("Enables 「**" + super.getSpecificAbility(4921).getName() + "**」 and 「**" + super.getSpecificAbility(4925).getName() + "**」 for 1 use (shared)");
				break;
			case 4921:
				ret.addStaticHit("50% Bonus BRV potency", 1);
				ret.removeHitDataById(5613);
				ret.removeHitDataById(5724);
				ret.addStaticHit("Reverts to 「**" + super.getSpecificAbility(4920).getName() + "**」 after use");
				break;
			case 4925:
				ret.addStaticHit("50% Bonus BRV potency", 1);
				ret.removeHitDataById(5618);
				ret.removeHitDataById(5724);
				ret.addStaticHit("Reverts to 「**" + super.getSpecificAbility(4924).getName() + "**」 after use");
				break;
			case 4928:
				ret.fixMergeAbility(5022);
			case 4927:
				ret.addStaticHit("Enabled if used right after an ally turn", 0);
				ret.removeHitDataById(5708);
				ret.removeHitDataById(5709);
				ret.removeHitDataById(5710);
				ret.removeHitDataById(5711);
				ret.removeHitDataById(5712);
				ret.removeHitDataById(5713);
		}
		return ret;
	}
}
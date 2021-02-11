package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Jack")
public class Jack extends Unit{
	public Jack() {
		super("Jack", "jacky");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "JP":
						ids = new int[]{11232, 7925}; break;
					case "GL":
						ids = new int[]{8001, 7925}; break;
				}
				break;
			case HP:
				switch(region) {
					case "JP":
						ids = new int[]{11233, 7925}; break;
					case "GL":
						ids = new int[]{8002, 7925}; break;
				}
				break;
			case S1:
				ids = new int[]{7135, 7925};
				break;
			case S2:
				ids = new int[]{7142, 7925};
				break;
			case EX:
				ids = new int[]{7924, 7925};
				break;
			case AA:
				ids = new int[]{7928};
				break;
			case LD:
				ids = new int[]{11014};
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
			case 7925:
				ret.setName("Deliverance");
				ret.getDetails().setMovementCost(30);
				ret.getDetails().setChaseDmg(3);
				break;
			case 11232: //JP BRV
			case 8001: //GL BRV
			case 11233: //JP HP
			case 8002: //GL HP
				ret.addStaticHit("Triggers 「**" + this.getSpecificAbility(7925).getName() + "**」 after breaking a target");
				break;
			case 11014: //LD
				ret.getAilmentById(2020).setRank(-1);
				ret.getAilmentById(2020).getEffects().get(2).rankData = new String[] {"-10", "-15", "-20", "-30", "-50"};
				ret.getAilmentById(2020).getEffects().get(3).rankData = new String[] {"-10", "-15", "-20", "-25", "-30"};
				ret.addStaticAilmentEffect(2020, "+1 stack to target after taking any attack", 0);
			case 7142: //S2
				ret.fixRemoveDispels();
			case 7135: //S1
				ret.fixStupidCriticalDamage(1412, 50);
				ret.addStaticHit("Triggers 「**" + this.getSpecificAbility(7925).getName() + "**」 after attacking a broken target");
				break;
			case 7924: //EX
				ret.getAilmentById(1495).getEffects().get(1).rankData = new String[] {"000"+ret.getAilmentById(1495).getArgs()[0]};
				ret.addStaticHit("Triggers 「**" + this.getSpecificAbility(7925).getName() + "**」 after attacking a broken target");
				break;
		}
		return ret;
	}
}
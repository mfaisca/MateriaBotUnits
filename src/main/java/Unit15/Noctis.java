package Unit15;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Noctis")
public class Noctis extends Unit{
	public Noctis() {
		super("Noctis", "noct", "lucis", "caelum", "noct gar", "sasuke");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4325, 4326}; break;
			case HP:
				ids = new int[]{4327, 9333, 9451}; break;
			case S1:
				ids = new int[]{9323, 9324, 9451}; break;
			case S2:
				ids = new int[]{9331, 9332, 9451}; break;
			case EX:
				ids = new int[]{7300, 7302};
				break;
			case AA:
				ids = new int[]{4068}; break;
			case LD:
				ids = new int[]{9284, 9451}; break;
			case BT:
				ids = new int[]{9285, 9217}; break;
			case CA:
				ids = new int[]{}; break;
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
			case 4459:
			case 4560:
			case 9450:
			case 9451: //Follow Up
				ret.getDetails().setMovementCost(30);
				ret.addStaticHit("Triggers after attacking if target has 「**" + super.getSpecificAilment(512).getName() + "**」", 0);
				break;
			case 4326: //BRV
			case 4328: //HP
				ret.addStaticHit("Enabled when 「**" + super.getSpecificAilment(358).getName() + "**」 has 5 stacks", 0);
				break;
			case 4316: //S1+ GL
			case 9324: //S1+ JP
				ret.removeHitDataById(688);
				ret.addStaticHit("Enabled after using 「**" + super.getSpecificAbility(4315).getName() + "**」 for 1 use", 0);
				break;
			case 4324: //S2+ GL
			case 9332: //S2+ JP
				ret.removeHitDataById(689);
				ret.getAilmentById(358).setRank(-1);
				ret.addStaticHit("Enabled after using 「**" + super.getSpecificAbility(4323).getName() + "**」 for 1 use", 0);
				break;
			case 7302: //EX+
				ret.addStaticHit("Enabled if target is afflicted with 「**" + super.getSpecificAilment(512).getName() + "**」", 0);
			case 7300: //EX
			case 9284: //LD
				ret.getAilmentById(358).setRank(-1);
				break;
			case 9285: //BT
				break;
			case 9217: //BT Follow Up
				ret.setName("Moratta");
				break;
			case 4068: //AA
				ret.addStaticHit("Turns 「**" + super.getSpecificAbility(4315).getName() + "**」 to 「**" + super.getSpecificAbility(4316).getName() + "**」");
				ret.addStaticHit("Turns 「**" + super.getSpecificAbility(4323).getName() + "**」 to 「**" + super.getSpecificAbility(4324).getName() + "**」");
				break;
		}
		return ret;
	}
}
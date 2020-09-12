package Unit10;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Seymour")
public class Seymour extends Unit{
	public Seymour() {
		super("Seymour");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7067};
				break;
			case HP:
				ids = new int[]{7074};
				break;
			case S1:
				ids = new int[]{7066};
				break;
			case S2:
				ids = new int[]{7069};
				break;
			case EX:
				ids = new int[]{7126};
				break;
			case AA:
				ids = new int[]{7044};
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
			case 7066:
				ret.addStaticHit("Trigger 「**" + ret.getName() + "**」 if target HP <= 80%");
				break;
			case 7069:
				ret.addStaticHit("Trigger 「**" + super.getSpecificAbility(7066).getName() + "**」 if target HP <= 80%");
				break;
			case 7126:
				ret.getAilmentById(747).setRank(-1);
				ret.addStaticHit("Trigger 「**" + super.getSpecificAbility(7069).getName() + "**」 if target HP <= 80%");
				ret.addStaticHit("Trigger 「**" + super.getSpecificAbility(7066).getName() + "**」 if target HP <= 30%");
				break;
		}
		return ret;
	}
}
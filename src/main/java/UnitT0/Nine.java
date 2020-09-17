package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Nine")
public class Nine extends Unit{
	public Nine() {
		super("Nine");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9375};
				break;
			case HP:
				ids = new int[]{9376};
				break;
			case S1:
				ids = new int[]{7609};
				break;
			case S2:
				ids = new int[]{7617};
				break;
			case EX:
				ids = new int[]{9374};
				break;
			case AA:
				ids = new int[]{7627};
				break;
			case LD:
				ids = new int[]{9377};
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
		if(ret == null) return null;
		switch(ret.getId()) {
			case 7609: //S1
			case 7617: //S2
				Ailment a = ret.getAilmentById(1459);
				ret.getDetails().getAilments().remove(a);
				if(ret.getAilmentById(1459) != null)
					ret.getDetails().getAilments().remove(ret.getAilmentById(1459));
				ret.getDetails().getAilments().add(a);
				a.setRank(-1);
				ret.addStaticAilmentEffect(1459, "+1 stack to self when used against 2+ enemies", 0);
				break;
			case 9374: //EX
				ret.addStaticAilmentEffect(1468, "Potencies double when against 2+ enemies", 0);
				break;
			case 9377: //LD
				if(ret.getAilmentById(1712) == null)
					ret.getDetails().getAilments().add(this.getSpecificAilment(1712));
				ret.getHitDataById(10054).setArguments(new Integer[] {1712});
				ret.addStaticAilmentEffect(1701, "After taking an HP hit, transforms to 「**" + ret.getAilmentById(1712).getName() + "**」");
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(id) {
			
		}
		return ret;
	}
}
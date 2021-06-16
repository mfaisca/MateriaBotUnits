package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.King")
public class King extends Unit{
	public King() {
		super("King");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6319};
				break;
			case HP:
				ids = new int[]{614};
				break;
			case S1:
				ids = new int[]{6316};
				break;
			case S2:
				ids = new int[]{6318};
				break;
			case EX:
				ids = new int[]{6311};
				break;
			case AA:
				ids = new int[]{6323};
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
			case 6318:
				ret.fixMergeAbility(6677);
				break;
		}
		return ret;
	}
}
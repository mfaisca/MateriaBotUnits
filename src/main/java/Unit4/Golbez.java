package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Golbez")
public class Golbez extends Unit{
	public Golbez() {
		super("Golbez", "swolbez", "swol");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{3569};
				break;
			case HP:
				ids = new int[]{6234};
				break;
			case S1:
				ids = new int[]{3553, 3554, 3555};
				break;
			case S2:
				ids = new int[]{3565, 3566, 3567};
				break;
			case EX:
				ids = new int[]{6233};
				break;
			case AA:
				ids = new int[]{6219};
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
		Ailment shadowDragon = ret.getAilmentById(811);
		if(shadowDragon != null)
			shadowDragon.setRank(-1);
		switch(ret.getId()) {
			
		}
		return ret;
	}
}
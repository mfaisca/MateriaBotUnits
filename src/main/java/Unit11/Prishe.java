package Unit11;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Prishe")
public class Prishe extends Unit{
	public Prishe() {
		super("Prishe");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8323};
				break;
			case HP:
				ids = new int[]{8324};
				break;
			case S1:
				ids = new int[]{8069, 8070, 8080};
				break;
			case S2:
				ids = new int[]{8074, 8076};
				break;
			case EX:
				ids = new int[]{8082};
				break;
			case AA:
				ids = new int[]{6653};
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
			case 8323:
			case 8324:
				ret.addStaticHit("Enabled after using 「**" + super.getSpecificAbility(8074).getName() + "**」 for 10 turns", 0);
				break;
			case 8069: //One Ilm
				ret.addStaticHit("Enables 「**" + super.getSpecificAbility(8070).getName() + "**」 for 1 use (shared)", 0);
				ret.addStaticHit("Enables 「**" + super.getSpecificAbility(8076).getName() + "**」 for 1 use (shared)", 1);
				break;
			case 8070: //Transfixion
				ret.removeHitDataById(688);
				ret.removeHitDataById(689);
				ret.addStaticHit("Enabled after using 「**" + super.getSpecificAbility(8069).getName() + "**」 for 1 use (shared)", 0);
				break;
			case 8080: //Fusion
				ret.removeHitDataById(688);
				ret.removeHitDataById(689);
				ret.addStaticHit("Enabled after using 「**" + super.getSpecificAbility(8074).getName() + "**」 for 1 use (shared)", 0);
				break;
			case 8074: //Spinning
				ret.addStaticHit("Enables 「**BRV+**」 and 「**HP+**」 for 10 turns", 0);
				ret.addStaticHit("Enables 「**" + super.getSpecificAbility(8080).getName() + "**」 for 1 use (shared)", 1);
				ret.addStaticHit("Enables 「**" + super.getSpecificAbility(8076).getName() + "**」 for 1 use (shared)", 2);
				break;
			case 8076: //Detonation
				ret.removeHitDataById(688);
				ret.removeHitDataById(689);
				ret.removeHitDataById(7806);
				ret.addStaticHit("Enabled after using 「**" + super.getSpecificAbility(8069).getName() + "**」 or 「**" + super.getSpecificAbility(8074).getName() + "**」 for 1 use (shared)", 0);
				Ability.Details.Hit_Data hd = new Ability.Details.Hit_Data();
				hd.setEffect(new Ability.Details.Hit_Data.Effect(Ability.Details.Hit_Data.EffectType.E135, 1));
				hd.setArguments(new Integer[] {50});
				ret.getDetails().getHits().add(0, hd);
				ret.removeAilmentById(1412);
				break;
		}
		return ret;
	}
}
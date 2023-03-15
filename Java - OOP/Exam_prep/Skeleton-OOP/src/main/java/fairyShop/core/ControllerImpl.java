package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{


    private HelperRepository<Helper> helperRepository = new HelperRepository<>();

    private PresentRepository<Present> presentRepository = new PresentRepository<>();

    private Shop shop = new ShopImpl();


    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        this.helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = this.helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        } else {
            Instrument instrument = new InstrumentImpl(power);
            helper.addInstrument(instrument);
            return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
        }
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> helpersOver50Energy = new ArrayList<>();

        for (Helper currentHelper : this.helperRepository.getModels()) {
            if (currentHelper.getEnergy() > 50) {
                helpersOver50Energy.add(currentHelper);
            }
        }
        if (helpersOver50Energy.isEmpty()) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }
        int countInstruments = 0;
        Present present = presentRepository.findByName(presentName);
        for (Helper helper : helpersOver50Energy) {
            shop.craft(present, helper);
            countInstruments += helper.getInstruments().stream().filter(Instrument::isBroken).count();
            if (present.isDone()) {
                break;
            }
        }
        if (present.isDone()) {
            return String.format(PRESENT_DONE, presentName, "done") +
            String.format(COUNT_BROKEN_INSTRUMENTS, countInstruments);
        } else {
            return String.format(PRESENT_DONE, presentName, "not done") +
                    String.format(COUNT_BROKEN_INSTRUMENTS, countInstruments);
        }
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!",
                (int) presentRepository.getModels().stream().filter(Present::isDone).count()));
        sb.append(System.lineSeparator());
        sb.append("Helpers info:");
        sb.append(System.lineSeparator());
        for (Helper helper : this.helperRepository.getModels()) {
            sb.append(String.format("Name: %s", helper.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Energy: %d", helper.getEnergy()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Instruments: %d not broken left",
                    (int) helper.getInstruments().stream().filter(i -> !i.isBroken()).count()));
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}

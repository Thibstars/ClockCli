package be.thibaulthelsmoortel.clockcli;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

/**
 * @author Thibault Helsmoortel
 */
class VersionProviderTest {

    private VersionProvider versionProvider;

    @BeforeEach
    void setUp() {
        this.versionProvider = new VersionProvider();
    }

    @Test
    void shouldReturnImplementationVersion() {
        String version = Arrays.toString(versionProvider.getVersion());

        Assertions.assertTrue(StringUtils.isNotBlank(version), "Version must not be blank.");
    }

    @Test
    void shouldReturnActualImplementationVersion() {
        Package pack = mock(Package.class);
        String version = "anActualVersion";
        when(pack.getImplementationVersion()).thenReturn(version);
        versionProvider.setPack(pack);

        String returnedVersion = Arrays.toString(versionProvider.getVersion());

        Assertions.assertTrue(StringUtils.isNotBlank(returnedVersion), "Version must not be blank.");
        Assertions.assertEquals(Arrays.toString(new String[]{version}), returnedVersion, "Versions must match.");
    }
}

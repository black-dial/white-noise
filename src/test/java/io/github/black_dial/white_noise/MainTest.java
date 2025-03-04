package io.github.black_dial.white_noise;

import io.github.black_dial.white_noise.cli.Session;
import io.github.black_dial.white_noise.data.TaskTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MainTest {
    public static final String UNKNOWN_COMMAND = "unknown";

    @Mock
    private Session session;

    private void verifyNoSessionInteraction() {
        verify(session, never()).add(any());
        verify(session, never()).list(any());
        verify(session, never()).check(any());
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        Main.setSession(session);
    }

    @Test
    void shouldCallAdd() {
        Main.main(new String[]{Main.Verb.ADD.toString().toLowerCase(), TaskTest.TEST_DESC});

        ArgumentCaptor<String[]> captor = ArgumentCaptor.forClass(String[].class);
        verify(session).add(captor.capture());
        assertArrayEquals(new String[]{TaskTest.TEST_DESC}, captor.getValue());
    }

    @Test
    void shouldCallList() {
        Main.main(new String[]{Main.Verb.LIST.toString().toLowerCase()});

        verify(session).list(new String[]{});
    }

    @Test
    void shouldCallCheck() {
        Main.main(new String[]{Main.Verb.CHECK.toString().toLowerCase(), TaskTest.TEST_ID.toString()});

        ArgumentCaptor<String[]> captor = ArgumentCaptor.forClass(String[].class);
        verify(session).check(captor.capture());
        assertArrayEquals(new String[]{TaskTest.TEST_ID.toString()}, captor.getValue());
    }

    @Test
    void shouldShowUsageOnNoArgs() {
        Main.main(new String[]{});

        verifyNoSessionInteraction();
    }

    @Test
    void shouldHandleUnknownCommand() {
        Main.main(new String[]{UNKNOWN_COMMAND});

        verifyNoSessionInteraction();
    }
}
